package com.example.template.utils.extensions

import android.content.Context
import android.net.ConnectivityManager

        fun Context.isNetworkConnected(): Boolean {
            val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeInfo = manager.activeNetworkInfo
            activeInfo != null && activeInfo.isConnectedOrConnecting
            return activeInfo != null && activeInfo.isConnected
        }

