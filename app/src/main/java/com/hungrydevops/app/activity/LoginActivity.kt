package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.hungrydevops.app.R
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
        }

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }

        binding.btn2.setOnClickListener {
            if(validate())
                startActivity(Intent(this@LoginActivity,OtpActivity::class.java))
        }


        binding.toggle.setOnClickListener {
            // Toggle password visibility
            val isPasswordVisible = binding.edtPassword.inputType == (InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

            binding.edtPassword.inputType = if (isPasswordVisible) {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }

            // Move the cursor to the end of the text
            binding.edtPassword.setSelection((it as EditText).text?.length ?: 0)

            // Change the eye icon based on the password visibility state
            binding.toggle.setImageResource(if (isPasswordVisible) R.drawable.ic_eye_show else R.drawable.ic_eye_hide)
        }
    }

    private fun validate(): Boolean {
        if(binding.edtPassword.text.isEmpty()){
            binding.edtPassword.error="Please enter Email ID"
            shakeView(binding.view1)
            return false
        }
        if(binding.edtPassword.text.isEmpty()){
            toast("Please enter password")
            shakeView(binding.view2)
            return false
        }
        return true
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