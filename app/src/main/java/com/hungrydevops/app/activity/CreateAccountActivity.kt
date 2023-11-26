package com.hungrydevops.app.activity

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityCreateAccountBinding
import java.util.Calendar

class CreateAccountActivity : BaseActivity() {

    val binding by lazy{
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn2.setOnClickListener {
//            if(validate())
            startActivity(Intent(this@CreateAccountActivity,AlmostDoneActivity::class.java))
        }
        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }
        binding.edtDob.setOnClickListener {
            val myCalender = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { viewaa, year, month, dayofMonth ->
                    binding.edtDob.setText("$year-${month + 1}-$dayofMonth")
                    binding.edtDob.setError(null)
                },
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            )
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400
            dpd.show()
        }

    }
    private fun validate(): Boolean {
        if(binding.edtEmail.text.isEmpty()){
            binding.edtEmail.error="Please enter Email ID"
            shakeView(binding.view1)
            return false
        }
        if(binding.edtDob.text.isEmpty()){
            toast("Please enter Dob")
            shakeView(binding.view2)
            return false
        }
        if(binding.edtPhoneNo.text.isEmpty()){
            binding.edtPhoneNo.error="Please enter phone no."
            shakeView(binding.view3)
            return false
        }
        return true
    }
}