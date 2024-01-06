package com.hungrydevops.app.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hungrydevops.app.common.DialogUtil

open class BaseActivity : AppCompatActivity() {
    private var mProgressDialog: ProgressDialog? = null
    private var lastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    protected fun View.setSingleClickListener(interval: Int = 1000, onSingleClick: (View) -> Unit) {
        this.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < interval) return@setOnClickListener
            lastClickTime = SystemClock.elapsedRealtime()
            onSingleClick(it)
        }
    }

    fun saveSharedPreferences(img:String,name:String,email:String,dob:String,gender:String){
        val sp=getSharedPreferences("Data",Context.MODE_PRIVATE)
        val editor=sp.edit()
        editor.putString("img",img)
        editor.putString("full_name",name)
        editor.putString("email",email)
        editor.putString("dob", dob)
        editor.putString("gender", gender)
        editor.apply()
    }

    fun getSharedPreferences(data:String) : String{
        val sp=getSharedPreferences("Data",Context.MODE_PRIVATE)
        return sp.getString(data,"").toString()
    }

    fun showLoading() {
        mProgressDialog = DialogUtil.showLoadingDialog(this@BaseActivity, "Base Activity")
        mProgressDialog!!.setCancelable(true)
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun makeToast(msg: String?) {
        Looper.prepare()
        Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
        Looper.loop()
    }

    fun hidekeyboard(view: View) {
        view.clearFocus()
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun shakeView(view: View) {
        val shakeAnimation = TranslateAnimation(0f, 10f, 0f, 0f)
        shakeAnimation.duration = 500
        shakeAnimation.interpolator = AnimationUtils.loadInterpolator(
            this,
            android.R.interpolator.cycle
        )

        view.startAnimation(shakeAnimation)
    }

}
