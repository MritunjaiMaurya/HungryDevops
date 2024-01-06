package com.hungrydevops.app.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hungrydevops.app.common.DialogUtil

open class BaseFragment : Fragment(){
    private var mProgressDialog: ProgressDialog? = null
    private var lastClickTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    fun showLoading() {
        mProgressDialog = DialogUtil.showLoadingDialog(requireActivity(), "Base Fragment")
        mProgressDialog!!.setCancelable(true)
    }

    fun getSharedPreferences(data:String) : String{
        val sp=requireActivity().getSharedPreferences("Data",Context.MODE_PRIVATE)
        return sp.getString(data,"").toString()
    }


    protected fun View.setSingleClickListener(interval: Int = 1000, onSingleClick: (View) -> Unit) {
        this.setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastClickTime < interval) return@setOnClickListener
            lastClickTime = SystemClock.elapsedRealtime()
            onSingleClick(it)
        }
    }

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val inputMethodManager: InputMethodManager =requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun makeToast(msg: String?) {
        Looper.prepare()
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        Looper.loop()
    }

    fun hidekeyboard(view: View) {
        view.clearFocus()
        val inputMethodManager =requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toast(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun shakeView(view: View) {
        val shakeAnimation = TranslateAnimation(0f, 10f, 0f, 0f)
        shakeAnimation.duration = 500
        shakeAnimation.interpolator = AnimationUtils.loadInterpolator(
            context,
            android.R.interpolator.cycle
        )

        view.startAnimation(shakeAnimation)
    }
}


