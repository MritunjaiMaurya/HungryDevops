package com.hungrydevops.app.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.tvResend.text="Didn't receive the otp? RESEND"
        binding.tvResend.setSingleClickListener {
            countDownTimer()
        }

        binding.btn2.setSingleClickListener {
            showBottomSheet()
        }
        binding.constraintOtp.setSingleClickListener {
            hidekeyboard(binding.constraintOtp)
        }
        binding.edtOne.requestFocus()

        showSoftKeyboard(binding.edtOne)

        binding.edtOne.addTextChangedListener(textWatcher)
        binding.edtTwo.addTextChangedListener(textWatcher)
        binding.edtThree.addTextChangedListener(textWatcher)
        binding.edtFour.addTextChangedListener(textWatcher)

        binding.edtFour.setOnKeyListener(keyEvent)
        binding.edtThree.setOnKeyListener(keyEvent)
        binding.edtTwo.setOnKeyListener(keyEvent)
    }

    private fun countDownTimer() {
        object : CountDownTimer(32000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvResend.isClickable = false
                val countdownText = "You can resend code in ${(millisUntilFinished / 1000)} s"
                val spannableString = SpannableString(countdownText)

                // Define the start and end indices for the colored part
                val start = countdownText.indexOf((millisUntilFinished / 1000).toString())
                val end = start + (millisUntilFinished / 1000).toString().length

                // Set the color for the specified part
                val color = resources.getColor(R.color.secondary)
                spannableString.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                // Set the SpannableString to the TextView
                binding.tvResend.text = spannableString
            }

            override fun onFinish() {
                binding.tvResend.isClickable = true
                binding.tvResend.text = "Click to resend"
            }
        }.start()
    }



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

    var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val text=s.toString()
            val inputMethodManager=getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            when(currentFocus){
                binding.edtOne-> if(text.length==1){ binding.edtTwo.requestFocus()}
                binding.edtTwo-> if(text.length==1){ binding.edtThree.requestFocus()}
                binding.edtThree-> if(text.length==1) {binding.edtFour.requestFocus()}
                binding.edtFour-> if(text.length==1) inputMethodManager.hideSoftInputFromWindow(currentFocus?.getWindowToken(),0)
            }
        }
    }
    //For backward shifting of focus/cursor one by one in edittext when deleting OTP(using backspace button)
    var keyEvent: View.OnKeyListener=object : View.OnKeyListener{
        override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
            val code4=binding.edtFour
            val code2=binding.edtTwo
            val code3=binding.edtThree
            val code1=binding.edtOne
            if(event?.action== KeyEvent.ACTION_DOWN&&keyCode== KeyEvent.KEYCODE_DEL) {
                if(code4.text.isEmpty()&&getCurrentFocus()==binding.edtFour){
                    code3.setText(null)
                    code3.requestFocus()
                    return true
                }
                if(code3.text.isEmpty()&&getCurrentFocus()==binding.edtThree){
                    code2.setText(null)
                    code2.requestFocus()
                    return true
                }
                if(code2.text.isEmpty()&&getCurrentFocus()==binding.edtTwo){
                    code1.setText(null)
                    code1.requestFocus()
                    return true
                }
            }
            return false
        }
    }
}