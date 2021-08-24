package com.example.template.ui.main_screen.permission_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.template.core.base.BaseViewModel
import javax.inject.Inject

class PermissionViewModel @Inject constructor() : BaseViewModel() {

    private val _msgToShow = MutableLiveData<String>()
    val msgToShow: LiveData<String> = _msgToShow

    fun checkPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.SEND_SMS, android.Manifest.permission.CAMERA
        )

        checkAndRequestPermissions(permissions, true,{
            _msgToShow.value = "All permission granted"
        },{_, _ ->

        })

    }
}
