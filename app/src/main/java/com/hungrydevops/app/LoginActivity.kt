package com.hungrydevops.app

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

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }

    }
}