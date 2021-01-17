package com.hoang.moviedblearning.utils

import android.content.Context
import android.graphics.Color
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hoang.moviedblearning.R

fun View.setSingleClick(timeout: Long = 1000L, execution: () -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0
        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < timeout) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            execution.invoke()
        }
    })
}

fun Fragment.showSnackBar(
    onClick: (() -> Unit)? = null,
    layout: View,
    resourceIdMessage: Int,
    resourceIdActionTitle: Int = R.string.message_empty,
    actionTextColor: Int = Color.WHITE,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    Snackbar.make(layout, resourceIdMessage, duration).setAction(getString(resourceIdActionTitle)) {
        onClick?.invoke()
    }
        .setActionTextColor(actionTextColor)
        .show()
}

fun View.hideKeyBoard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
}
