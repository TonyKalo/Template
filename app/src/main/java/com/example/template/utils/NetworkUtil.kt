package com.example.template.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil {
    companion object {
        fun isNetworkConnected(from: Context?): Boolean = from?.let {
            val manager = from.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            activeInfo != null && activeInfo.isConnectedOrConnecting
        } ?: false


    }
}