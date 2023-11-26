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
//            if(validate())
            startActivity(Intent(this@AlmostDoneActivity,SuccessActivity::class.java))
        }
        binding.constraint.setSingleClickListener {
            hidekeyboard(binding.constraint)
        }
    }
    private fun validate(): Boolean {
        if(binding.edtUsername.text.isEmpty()){
            binding.edtUsername.error="Please enter username"
            shakeView(binding.view1)
            return false
        }
        if(binding.edtEmail.text.isEmpty()){
            binding.edtEmail.error="Please enter email"
            shakeView(binding.view2)
            return false
        }
        if(binding.edtPassword.text.isEmpty()){
            binding.edtPassword.error="Please enter password"
            shakeView(binding.view3)
            return false
        }
        return true
    }
}