package com.hungrydevops.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hungrydevops.app.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
    }
}