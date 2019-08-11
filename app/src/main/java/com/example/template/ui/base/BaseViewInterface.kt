package com.example.template.ui.base

import com.example.template.ui.base.callbacks.PermissionCallback

interface BaseViewInterface{
    fun showCancelableProgress()
    fun hideProgress()
    fun showNonCancelableProgress()
    fun showSnackbar(msg:String)
    fun requestPermission(permissions : Array<String>)
    fun requestPermissionRationale(permission : String):Boolean
    fun openAppSettingsDialog(msg:String)
    fun openRetryDialog(msg:String)
}