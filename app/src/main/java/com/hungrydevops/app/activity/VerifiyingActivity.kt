package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.google.firebase.auth.FirebaseAuth
import com.hungrydevops.app.R
import com.hungrydevops.app.databinding.ActivityVerifyingBinding

class VerifiyingActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityVerifyingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val animation= AnimationUtils.loadAnimation(this, R.anim.rounding)
        binding.imgRounding.startAnimation(animation)

        startEmailVerificationCheck()
    }
    private fun startEmailVerificationCheck() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                checkEmailVerification(handler, this)
            }
        }
        handler.postDelayed(runnable, 5000) // Check every 5 seconds
    }

    private fun checkEmailVerification(handler: Handler, runnable: Runnable) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful && user.isEmailVerified) {
                // Email is verified, navigate to Main Activity
                FirebaseAuth.getInstance().currentUser
                startActivity(Intent(this@VerifiyingActivity, SuccessActivity::class.java))
                handler.removeCallbacks(runnable) // Stop the repeating task
                finish()
            } else {
                handler.postDelayed(runnable, 5000) // Repeat after 5 seconds
            }
        }
    }
}
