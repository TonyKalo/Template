package com.example.template.ui.base.callbacks

interface PermissionCallback {
    fun onSuccess()
    fun onFail(deniedPermiss: Array<String>, needExternalPermiss: Array<String>)
}