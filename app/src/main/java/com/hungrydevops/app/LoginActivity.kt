package com.hungrydevops.app

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.text.InputType
import android.widget.ImageView
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


        binding.toggle.setOnClickListener{
            if(it.background==resources.getDrawable(R.drawable.ic_eye_hide)){
                binding.edtPassword.inputType=InputType.TYPE_CLASS_TEXT
                (it as ImageView).setImageResource(R.drawable.ic_eye_show)
            }
            else{
                binding.edtPassword.inputType=InputType.TYPE_TEXT_VARIATION_PASSWORD
                it.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.ic_eye_hide))
            }
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