package com.hungrydevops.app.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityCreateAccountBinding
import java.util.Calendar

class CreateAccountActivity : BaseActivity() {

    val binding by lazy{
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }
    var img:String="1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnn.btn2.text="Continue"

        binding.btnn.btn2.setOnClickListener {
            if(validate()){

                val intent = Intent(this@CreateAccountActivity, AlmostDoneActivity::class.java)
                intent.putExtra("img",img)
                intent.putExtra("name",binding.edtFullName.text.toString())
                intent.putExtra("dob", binding.edtDob.text.toString())
                intent.putExtra("gender", binding.genderGroup.findViewById<RadioButton>(binding.genderGroup.checkedRadioButtonId).text.toString())
                startActivity(intent)
            }
        }

        binding.edtDob.setOnClickListener{
            hidekeyboard(it)
            binding.edtDob.error=null
            val myCalender = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { viewaa, year, month, dayofMonth ->
                    val formattedMonth = String.format("%02d", month + 1)
                    val formattedDayOfMonth = String.format("%02d", dayofMonth)
                    binding.edtDob.setText("$year-$formattedMonth-$formattedDayOfMonth")
                    binding.edtDob.setError(null)
                },
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            )
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400
            dpd.show()
        }

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }
        binding.imgBack.setSingleClickListener{
            finish()
        }
        binding.imgEdit.setOnClickListener {
            bottomSheet()
        }


    }

    private fun bottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_avatar, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()

        view.findViewById<ImageView>(R.id.img1).setOnClickListener {
            onActivityResult(101,1,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img2).setOnClickListener {
            onActivityResult(102,2,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img3).setOnClickListener {
            onActivityResult(103,3,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img4).setOnClickListener {
            onActivityResult(104,4,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img5).setOnClickListener {
            onActivityResult(105,5,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img6).setOnClickListener {
            onActivityResult(106,6,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img7).setOnClickListener {
            onActivityResult(107,7,intent)
            bottomSheetDialog.dismiss()
        }
        view.findViewById<ImageView>(R.id.img8).setOnClickListener {
            onActivityResult(108,8,intent)
            bottomSheetDialog.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==101){
            binding.imgAvatar.setImageResource(R.drawable.avatar_1)
            img="1"
        }else if(requestCode==102){
            binding.imgAvatar.setImageResource(R.drawable.avatar_2)
            img="2"
        }
        else if(requestCode==103){
            binding.imgAvatar.setImageResource(R.drawable.avatar_3)
            img="3"
        }
        else if(requestCode==104){
            binding.imgAvatar.setImageResource(R.drawable.avatar_4)
            img="4"
        }
        else if(requestCode==105){
            binding.imgAvatar.setImageResource(R.drawable.avatar_5)
            img="5"
        }
        else if(requestCode==106){
            binding.imgAvatar.setImageResource(R.drawable.avatar_6)
            img="6"
        }
        else if(requestCode==107){
            binding.imgAvatar.setImageResource(R.drawable.avatar_7)
            img="7"
        }
        else if(requestCode==108){
            binding.imgAvatar.setImageResource(R.drawable.avatar_8)
            img="8"
        }
    }

    private fun validate(): Boolean {
        if(binding.edtFullName.text.isEmpty()){
            binding.edtFullName.error="Please enter your name"
            shakeView(binding.view1)
            return false
        }
        if(binding.edtDob.text.isEmpty()){
            binding.edtDob.error="Please select DOB"
            shakeView(binding.view1)
            return false
        }
        return true
    }
}