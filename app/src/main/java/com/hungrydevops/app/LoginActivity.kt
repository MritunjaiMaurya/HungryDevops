package com.hungrydevops.app

import android.content.Intent
import android.os.Bundle
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityLoginBinding

class LoginActivity: BaseActivity() {
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }
    }
}