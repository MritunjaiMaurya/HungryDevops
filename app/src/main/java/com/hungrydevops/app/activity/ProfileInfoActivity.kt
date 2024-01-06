package com.hungrydevops.app.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.hungrydevops.app.R
import com.hungrydevops.app.base.BaseActivity
import com.hungrydevops.app.databinding.ActivityProfileInfoBinding
import java.io.UnsupportedEncodingException
import java.util.Calendar

class ProfileInfoActivity : BaseActivity() {

    val binding by lazy {
        ActivityProfileInfoBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imgAvatar.setImageResource(when(getSharedPreferences("img")){
            "1"-> R.drawable.avatar_1
            "2"->R.drawable.avatar_2
            "3"->R.drawable.avatar_3
            "4"->R.drawable.avatar_4
            "5"->R.drawable.avatar_5
            "6"->R.drawable.avatar_6
            "7"->R.drawable.avatar_7
            "8"->R.drawable.avatar_8
            else ->R.drawable.avatar_1
        })
        binding.editText.setText(getSharedPreferences("full_name"))
        binding.editText2.setText(getSharedPreferences("email"))
        binding.editText3.setText(getSharedPreferences("dob"))
        binding.editText4.setText(getSharedPreferences("gender"))

        binding.constraint.setOnClickListener {
            hidekeyboard(it)
        }

        binding.imgEdit.setOnClickListener {
            bottomSheet()
        }

        binding.imgEditDob.setOnClickListener{
            val myCalender = Calendar.getInstance()
            val dpd = DatePickerDialog(
                this,
                { viewaa, year, month, dayofMonth ->
                    val formattedMonth = String.format("%02d", month + 1)
                    val formattedDayOfMonth = String.format("%02d", dayofMonth)
                    binding.editText3.setText("$year-$formattedMonth-$formattedDayOfMonth")
                    binding.editText3.setError(null)
                    update("dob", binding.editText3.text.toString())
                },
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            )
            dpd.datePicker.maxDate = System.currentTimeMillis() - 86400
            dpd.show()
        }

        binding.imgEditGender.setOnClickListener{
            val dialog= Dialog(this)
            val view=layoutInflater.inflate(R.layout.dialog_gender_select, null)
            dialog.setContentView(view)
            dialog.show()

            view.findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {
                binding.editText4.setText(view.findViewById<RadioButton>(
                    view.findViewById<RadioGroup>(R.id.gender_group).checkedRadioButtonId).text)
                update("gender", binding.editText4.text.toString())
                dialog.dismiss()
            }
        }

        binding.imgBack.setOnClickListener{
            onBackPressed()
        }

    }

    private fun update(s:String,value:String){
        val sp=getSharedPreferences("Data", Context.MODE_PRIVATE)
        val editor=sp.edit()
        editor.putString(s,value)
        editor.apply()
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(encodeEmail(getSharedPreferences("email")))
            .child(s).setValue(value)
    }

    fun encodeEmail(email: String): String {
        return try {
            val data = email.toByteArray(charset("UTF-8"))
            Base64.encodeToString(data, Base64.NO_WRAP)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("Unsupported Encoding", e)
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
            update("img","1")
        }else if(requestCode==102){
            binding.imgAvatar.setImageResource(R.drawable.avatar_2)
            update("img","2")
        }
        else if(requestCode==103){
            binding.imgAvatar.setImageResource(R.drawable.avatar_3)
            update("img","3")
        }
        else if(requestCode==104){
            binding.imgAvatar.setImageResource(R.drawable.avatar_4)
            update("img","4")
        }
        else if(requestCode==105){
            binding.imgAvatar.setImageResource(R.drawable.avatar_5)
            update("img","5")
        }
        else if(requestCode==106){
            binding.imgAvatar.setImageResource(R.drawable.avatar_6)
            update("img","6")
        }
        else if(requestCode==107){
            binding.imgAvatar.setImageResource(R.drawable.avatar_7)
            update("img","7")
        }
        else if(requestCode==108){
            binding.imgAvatar.setImageResource(R.drawable.avatar_8)
            update("img","8")
        }
    }
}