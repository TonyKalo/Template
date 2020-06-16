package com.example.template.ui.main_screen.permission_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.template.core.base.BaseViewModel
import com.example.template.core.base.callbacks.PermissionCallback
import javax.inject.Inject

class PermissionViewModel @Inject constructor() : BaseViewModel() {

    private val _msgToShow = MutableLiveData<String>()
    val msgToShow: LiveData<String> = _msgToShow

    fun checkPermissions() {
        val permissions = arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.SEND_SMS, android.Manifest.permission.CAMERA
        )

        checkAndRequestPermissions(permissions, true, object : PermissionCallback {
            override fun onSuccess() {
                _msgToShow.value = "All permission granted"
            }

            override fun onFail(deniedPermiss: Array<String>, needExternalPermiss: Array<String>) {
            }
        })
    }
}
