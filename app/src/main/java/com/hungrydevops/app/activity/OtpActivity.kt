package com.hungrydevops.app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.hungrydevops.app.MainActivity
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityOtpBinding

class OtpActivity : BaseActivity() {

    val binding by lazy {
        ActivityOtpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn2.setSingleClickListener {
            var view:View=layoutInflater.inflate(R.layout.dialog_go_to_home,null)
            val dialog=BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
            findViewById<MaterialButton>(R.id.btn2).setSingleClickListener {
                startActivity(Intent(this@OtpActivity,MainActivity::class.java))
                finish()
            }

        }


    }
}