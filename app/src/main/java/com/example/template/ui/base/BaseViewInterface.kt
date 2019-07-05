package com.example.template.ui.base

import com.example.template.ui.base.callbacks.PermissionCallback

interface BaseViewInterface{
    fun showProgress()
    fun hideProgress()
    fun showSnackbar(msg:String)
    fun requestPermission(permissions : Array<String>)
}