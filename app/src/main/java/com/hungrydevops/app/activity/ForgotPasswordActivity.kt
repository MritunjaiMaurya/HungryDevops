package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hungrydevops.app.LoginActivity
import com.hungrydevops.app.R
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
            startActivity(Intent(this@ForgotPasswordActivity,LoginActivity::class.java))
            finish()
        }

    }
}