package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivitySuccessBinding

class SuccessActivity : BaseActivity() {

    val binding by lazy {
        ActivitySuccessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn2.setSingleClickListener {
            startActivity(Intent(this@SuccessActivity,MainActivity::class.java))
            finish()
        }

    }
}