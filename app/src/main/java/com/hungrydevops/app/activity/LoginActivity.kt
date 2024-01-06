package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.data.User
import com.hungrydevops.app.databinding.ActivityLoginBinding
import java.io.UnsupportedEncodingException

class LoginActivity: BaseActivity() {

    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var databaseReference: DatabaseReference
    val auth = FirebaseAuth.getInstance()
    private lateinit var googleSignInClient: GoogleSignInClient
    val RC_SIGN_IN : Int = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity,ForgotPasswordActivity::class.java))
        }

//        binding.constraint.setOnClickListener {
//            hidekeyboard(it)
//        }

        binding.imgBack.setOnClickListener{ finish()}

        binding.btnn.btn2.text="Sign in"

        binding.btnn.btn2.setOnClickListener {
            if(validate()) {
                loginUser(binding.edtEmail.text.toString(), binding.edtPassword.text.toString())
            }
        }

        binding.imgGoogleAuth.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


        binding.toggle.setOnClickListener {
            // Toggle password visibility
            val isPasswordVisible = binding.edtPassword.inputType == (InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

            binding.edtPassword.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }

            // Move the cursor to the end of the text
            binding.edtPassword.setSelection(binding.edtPassword.text?.length ?: 0)

            binding.toggle.setImageResource(if (isPasswordVisible) R.drawable.ic_eye_hide else R.drawable.ic_eye_show)
        }
    }

    private fun validate(): Boolean {
        if(binding.edtEmail.text.isEmpty()){
            binding.edtEmail.error="Please enter Email ID"
            shakeView(binding.view1)
            return false
        }
        if(binding.edtPassword.text.isEmpty()){
            toast("Please enter password")
            shakeView(binding.view2)
            return false
        }
        return true
    }

    fun loginUser(email: String, password: String) {
        showLoading()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration was successful
                    fetch(email)
                    checkEmailVerification()

                    // You can handle the user data or navigate to the next screen here
                } else {
                    hideLoading()
                    Toast.makeText(this@LoginActivity,"Invalid username and password", Toast.LENGTH_SHORT).show()
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthUserCollisionException) {
                        // User with the same email already exists
                        hideLoading()
                        Toast.makeText(this@LoginActivity,"Username and password with the same email already exists",
                            Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        hideLoading()
                        Toast.makeText(this@LoginActivity,"Try again after some time", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123) {
            showLoading()
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
                val acc: GoogleSignInAccount? = result?.signInAccount
                val displayName = acc?.displayName
                val email = acc?.email
                databaseReference= FirebaseDatabase.getInstance().getReference("Users")

                databaseReference.child(encodeEmail(email.toString())).addListenerForSingleValueEvent(object:
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(!snapshot.exists()){
                            val User= User("6",displayName,email,"YYYY-MM-DD","Male")
                            if (email != null) {
                                databaseReference.child(encodeEmail(email)).setValue(User)
                            }
                        }
                        if (email != null) {
                            fetch(email)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        hideLoading()
                    }
                })
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                hideLoading()
                Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun encodeEmail(email: String): String {
        return try {
            val data = email.toByteArray(charset("UTF-8"))
            Base64.encodeToString(data, Base64.NO_WRAP)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("Unsupported Encoding", e)
        }
    }

    // Authenticate with Firebase
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    hideLoading()
                    Toast.makeText(this, "Sign In failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun checkEmailVerification() {
        hideLoading()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && !user.isEmailVerified) {
            startActivity(Intent(this@LoginActivity, VerifiyingActivity::class.java))
            finish()
        }
        else {
            // Email is verified, proceed to the main activity
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

    }
    private fun fetch(email:String){
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")

        val query= databaseReference.orderByChild("email").equalTo(email)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    // Convert the snapshot to your data class
                    val user = userSnapshot.getValue(User::class.java)
                    if (user != null) {
                        user.img?.let { user.fullname?.let { it1 ->
                            user.email?.let { it2 ->
                                user.dob?.let { it3 ->
                                    user.gender?.let { it4 ->
                                        saveSharedPreferences(it,
                                            it1, it2, it3, it4
                                        )
                                    }
                                }
                            }
                        }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity,"Failed",Toast.LENGTH_SHORT).show()
            }
        })
    }
}