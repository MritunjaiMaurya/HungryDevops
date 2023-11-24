package com.hungrydevops.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.hungrydevops.app.activity.ForgotPasswordActivity
import com.hungrydevops.app.activity.OtpActivity
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity() {
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity,ForgotPasswordActivity::class.java))
            finish()
        }

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }

        binding.btn2.setOnClickListener {
            startActivity(Intent(this@LoginActivity,OtpActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            super.onBackPressed()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = System.currentTimeMillis()
        }
    }
}