package com.hungrydevops.app.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
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
            showBottomSheet()
        }
    }
//    private fun bottomSheet(){
//        val dialog=BottomSheetDialog(this)
//        var view:View=layoutInflater.inflate(R.layout.dialog_go_to_home,null)
//        dialog.setContentView(view)
//
//        findViewById<MaterialButton>(R.id.btn_bs2).setSingleClickListener {
//
//        }
//        dialog.show()
//    }
    private fun showBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_go_to_home, null)
        bottomSheetDialog.setContentView(view)

        val goToHomeButton = view.findViewById<MaterialButton>(R.id.btn_bs2)
        goToHomeButton.setSingleClickListener {
            startActivity(Intent(this@OtpActivity, MainActivity::class.java))
        }

        bottomSheetDialog.show()
    }
}