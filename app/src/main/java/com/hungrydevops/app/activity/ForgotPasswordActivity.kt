package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity() {

    val binding by lazy {
        ActivityForgotPasswordBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.imgBack.setSingleClickListener {
            startActivity(Intent(this@ForgotPasswordActivity, LoginActivity::class.java))
            finish()
        }

    }
}