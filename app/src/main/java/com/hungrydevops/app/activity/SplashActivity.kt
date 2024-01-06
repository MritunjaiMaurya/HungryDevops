package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.data.User
import com.hungrydevops.app.databinding.ActivitySplashBinding

class SplashActivity:BaseActivity(){

    val binding by lazy{
        ActivitySplashBinding.inflate(layoutInflater)
    }

    private lateinit var databaseReference: DatabaseReference

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val animation = AlphaAnimation(0.0f,1.0f)
        animation.duration=1000
        animation.startOffset=1000
        animation.fillAfter=true
        binding.imgSplash.startAnimation(animation)


        Handler().postDelayed({startActivity(Intent(this, OnboardingActivity::class.java))
        finish()
        },3000)

        abc()

    }

    private fun abc(){
        Handler(Looper.getMainLooper()).postDelayed({
            val currentUser = auth.currentUser
            if (currentUser != null) {
                navigateToHome()
                databaseReference= FirebaseDatabase.getInstance().getReference("Users")

                val query= databaseReference.orderByChild("email").equalTo(getSharedPreferences("email"))
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
                        Toast.makeText(this@SplashActivity,"Failedddddd", Toast.LENGTH_SHORT).show()
                        Log.e("sfnkfsndsjf", "onCancelled: ${error.message}",)
                    }
                })
            } else {
                navigateToLogin()
            }
        },3000)

    }

    private fun navigateToHome() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            fetch(getSharedPreferences("email"))
            if(user.isEmailVerified){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
                finish()
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
        finish()
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
                Toast.makeText(this@SplashActivity,"Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}