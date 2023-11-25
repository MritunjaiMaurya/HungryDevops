package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityAlmostDoneBinding

class AlmostDoneActivity : BaseActivity() {

    val binding by lazy {
        ActivityAlmostDoneBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn2.setSingleClickListener {
            startActivity(Intent(this@AlmostDoneActivity,SuccessActivity::class.java))
        }
        binding.constraint.setSingleClickListener {
            hidekeyboard(binding.constraint)
        }


    }
}