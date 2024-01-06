package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.data.User
import com.hungrydevops.app.databinding.ActivityAlmostDoneBinding
import java.io.UnsupportedEncodingException

class AlmostDoneActivity : BaseActivity() {

    val binding by lazy {
        ActivityAlmostDoneBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvHeading.text="Welcome ${intent.getStringExtra("name")} 👋"

        binding.btnn.btn2.setSingleClickListener {
            if(validate()){
                registerUser(binding.edtEmail.text.toString(),binding.edtPassword.text.toString())
            }
        }
        binding.constraint.setSingleClickListener {
            hidekeyboard(binding.constraint)
        }
        binding.imgBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.toggle2.setOnClickListener {
            // Toggle password visibility
            val isPasswordVisible = binding.edtPassword.inputType == (InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

            binding.edtPassword.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }

            binding.edtPassword.setSelection(binding.edtPassword.text?.length ?: 0)

            binding.toggle2.setImageResource(if (isPasswordVisible) R.drawable.ic_eye_hide else R.drawable.ic_eye_show)
        }
        binding.toggle4.setOnClickListener {
            // Toggle password visibility
            val isPasswordVisible = binding.edtPassword2.inputType == (InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

            binding.edtPassword2.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }

            binding.edtPassword2.setSelection(binding.edtPassword2.text?.length ?: 0)

            binding.toggle4.setImageResource(if (isPasswordVisible) R.drawable.ic_eye_hide else R.drawable.ic_eye_show)
        }

    }

    private fun validate(): Boolean {

        if(binding.edtEmail.text.isEmpty()){
            binding.edtEmail.error="Please enter email"
            shakeView(binding.view2)
            return false
        }
        if(binding.edtPassword.text.isEmpty()){
            binding.edtPassword.error="Please enter password"
            shakeView(binding.view3)
            return false
        }
        if(binding.edtPassword2.text.isEmpty()){
            binding.edtPassword2.error="Please enter retype password"
            shakeView(binding.view4)
            return false
        }
        if(!(binding.edtPassword.text.toString().equals(binding.edtPassword2.text.toString()))){
            Toast.makeText(this@AlmostDoneActivity,"Password not matched", Toast.LENGTH_SHORT).show()
            shakeView(binding.view4)
            return false
        }
        return true
    }

    fun registerUser(email: String, password: String) {
        showLoading()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser = task.result?.user
                    firebaseUser?.let { user ->
                        val fullName=intent.getStringExtra("name")

                        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
                        val User= User(intent.getStringExtra("img"),fullName,email,
                            intent.getStringExtra("dob").toString(),intent.getStringExtra("gender").toString())
                        databaseReference.child(encodeEmail(email)).setValue(User)
                    }
                    saveSharedPreferences(intent.getStringExtra("img").toString(),intent.getStringExtra("name").toString(),
                        binding.edtEmail.text.toString(),intent.getStringExtra("dob").toString(),intent.getStringExtra("gender").toString())
                    sendVerificationEmail()
                } else {
                    try {
                        hideLoading()
                        throw task.exception!!
                    } catch (e: FirebaseAuthUserCollisionException) {
                        // User with the same email already exists
                        Toast.makeText(this@AlmostDoneActivity,"User with the same email already exists",
                            Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        // Other errors
                        Toast.makeText(this@AlmostDoneActivity,e.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun sendVerificationEmail() {
        hideLoading()
        val user = FirebaseAuth.getInstance().currentUser
        user?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this@AlmostDoneActivity, VerifiyingActivity::class.java))
            } else {
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
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

    fun decodeEmail(encodedEmail: String): String {
        return try {
            val data = Base64.decode(encodedEmail, Base64.NO_WRAP)
            String(data, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("Unsupported Encoding", e)
        }
    }
}