package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity() {

    val binding by lazy {
        ActivityForgotPasswordBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnn.btn2.setOnClickListener {
            val email = binding.edtEmail.text.toString().trim()
            if (email.isEmpty()) {
                binding.edtEmail.error = "Email required"
                binding.edtEmail.requestFocus()
            }
            else{resetPassword(email)}
        }

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }

        binding.imgBack.setSingleClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun resetPassword(email: String) {
        showLoading()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_LONG).show()
                }
            }
        hideLoading()
    }
}