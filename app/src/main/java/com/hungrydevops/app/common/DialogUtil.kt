package com.hungrydevops.app.common

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.hungrydevops.app.R

object DialogUtil {
    private const val TAG = "DialogUtil"
    private var progressDialog: ProgressDialog? = null

    fun showLoadingDialog(activity: Activity?, callingPlace: String): ProgressDialog? {
        progressDialog = ProgressDialog(activity)
        progressDialog!!.show()
        if (progressDialog!!.window != null) {
            progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog!!.setContentView(R.layout.progress_dialog)
        progressDialog!!.isIndeterminate = true
        progressDialog!!.setCancelable(true)
        progressDialog!!.setCanceledOnTouchOutside(false)
        val animation= AnimationUtils.loadAnimation(activity,R.anim.rounding)
        progressDialog!!.findViewById<ImageView>(R.id.img_rounding).startAnimation(animation)
        return progressDialog
    }

    fun hideDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.hide()
        }
    }
}