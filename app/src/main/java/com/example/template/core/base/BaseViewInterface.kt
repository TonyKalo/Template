package com.example.template.core.base

interface BaseViewInterface {
    fun showCancelableProgress()
    fun hideProgress()
    fun showNonCancelableProgress()
    fun showSnackbar(msg: String)
    fun requestPermission(permissions: Array<String>)
    fun requestPermissionRationale(permission: ArrayList<String>): Unit?
    fun openAppSettingsDialog(msg: String): Unit?
    fun openRetryDialog(msg: String): Unit?
}
