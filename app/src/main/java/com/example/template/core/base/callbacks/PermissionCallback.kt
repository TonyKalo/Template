package com.example.template.core.base.callbacks

interface PermissionCallback {
    fun onSuccess()
    fun onFail(deniedPermiss: Array<String>, needExternalPermiss: Array<String>)
}
