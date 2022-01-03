package com.namanh.mindx.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {
    fun isNetworkConnected(context: Context?): Boolean {
        if (context == null) return false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}