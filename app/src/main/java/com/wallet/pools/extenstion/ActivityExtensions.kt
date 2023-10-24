package com.wallet.pools.extenstion

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.wallet.pools.R
import timber.log.Timber

internal fun Activity.showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
//        anchorView = view.rootView.findViewById(R.id.bottomNavigationView)
        show()
    }
}

internal fun Fragment.showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).apply {
//        anchorView = view.rootView.findViewById(R.id.bottomNavigationView)
        show()
    }
}

private var toast: Toast? = null
private var isCanCloseToast: Boolean = false

internal fun Context.showToast(message: String, canClose: Boolean = true) {
    isCanCloseToast = canClose
    toast?.cancel()
    val inflater = LayoutInflater.from(this)
    val layout: View = inflater.inflate(R.layout.layout_toast_custom, null)
    val textView: TextView = layout.findViewById(R.id.toast_text)
    textView.text = message
    toast = Toast(this)
    toast!!.duration = Toast.LENGTH_SHORT
    toast!!.view = layout
    toast!!.show()
}


internal fun Context.hideToast() {
    if (isCanCloseToast) {
        toast?.cancel()
    }
}


internal inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}

fun isAppOnForeground(context: Context): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val appProcesses = activityManager.runningAppProcesses
    val packageName = context.packageName
    for (appProcess in appProcesses) {
        if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == packageName) {
            Timber.i("HAOHAO isAppOnForeground true")

            return true

        }
    }
    Timber.i("HAOHAO isAppOnForeground false")

    return false
}
