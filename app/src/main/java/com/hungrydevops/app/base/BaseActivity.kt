package com.hungrydevops.app.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

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

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
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

}
