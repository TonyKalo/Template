package com.example.template.ui.base

interface BaseViewInterface {
    fun showCancelableProgress()
    fun hideProgress()
    fun showNonCancelableProgress()
    fun showSnackbar(msg: String)
    fun requestPermission(permissions: Array<String>)
    fun requestPermissionRationale(permission: ArrayList<String>)
    fun openAppSettingsDialog(msg: String)
    fun openRetryDialog(msg: String)
}
