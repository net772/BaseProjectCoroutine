package com.example.baseproject.extentions

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.example.baseproject.Application

fun Activity.showToast(message: String, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)

fun Activity.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)

fun Fragment.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)

fun AndroidViewModel.showToast(message: String, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)

fun AndroidViewModel.showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT): Unit =
    SingleToast.showToast(message, duration)


object SingleToast {

    private var toast: Toast? = null

    fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()

        toast = Toast.makeText(Application.applicationContext, message, duration)
        toast?.show()
    }

    fun showToast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) {
        toast?.cancel()

        toast = Toast.makeText(Application.applicationContext, message, duration)
        toast?.show()
    }

}