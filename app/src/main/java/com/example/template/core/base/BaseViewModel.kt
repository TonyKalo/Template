package com.example.template.core.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.PermissionChecker
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.R
import com.example.template.core.base.callbacks.PermissionCallback
import com.example.template.utils.extensions.isNetworkConnected
import com.google.gson.JsonSyntaxException
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection
import retrofit2.HttpException

open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var appContext: Context

    companion object {
        const val PERMISSION_REQUEST_CODE = 101
    }

    private val RETRY_MSG = 1
    private val APP_SETTINGS_MSG = 2

    private val isLoading = MutableLiveData<Boolean>()
    private val isLoadingNonCancelable = MutableLiveData<Boolean>()
    private val handleErrorString = MutableLiveData<String>()
    private val permissionsRequest = MutableLiveData<Array<String>>()
    private val permissionsRequestRationale = MutableLiveData<ArrayList<String>>()
    private val openRetryDialog = MutableLiveData<String>()
    private val openAppSettingsDialog = MutableLiveData<String>()

    private var externalPermissNeed: ArrayList<String> = ArrayList()
    private var onPermissDenied: ArrayList<String> = ArrayList()
    private var permissionCallback: PermissionCallback? = null
    private var permissions: Array<String>? = null
    private var dialogHandler: Boolean = true

    fun getPermissionRequest() = permissionsRequest
    fun getPermissionRequestRationale() = permissionsRequestRationale
    fun getAppSettingsRetryDialog() = openAppSettingsDialog
    fun getRetryDialog() = openRetryDialog
    fun getErrorHandler() = handleErrorString
    fun getIsLoading() = isLoading
    fun getIsLoadingNonCancelable() = isLoadingNonCancelable

    fun showLoaderNonCancelable(show: Boolean) {
        isLoadingNonCancelable.value = show
    }

    fun showLoader(show: Boolean) {
        isLoading.value = show
    }

    fun handleErrorString(msg: String) {
        handleErrorString.value = msg
    }

    fun handleError(e: Throwable) {
        if (!appContext.isNetworkConnected()) {
            handleErrorString.value = appContext.getString(R.string.err_no_net)
        } else if (e is HttpException) {
            when (e.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> handleErrorString.value =
                    appContext.getString(R.string.err_unauthorised)
                HttpsURLConnection.HTTP_FORBIDDEN -> handleErrorString.value =
                    appContext.getString(R.string.err_forbidden)
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> handleErrorString.value =
                    appContext.getString(R.string.err_internal_server)
                HttpsURLConnection.HTTP_BAD_REQUEST -> handleErrorString.value =
                    appContext.getString(R.string.err_bad_request)
                else -> handleErrorString.value = e.localizedMessage
            }
        } else if (e is JsonSyntaxException) {
            handleErrorString.value = appContext.getString(R.string.err_not_responding)
        } else {
            handleErrorString.value = e.localizedMessage
        }
    }

    @SuppressLint("WrongConstant")
    fun checkPermissions(permissions: Array<String>): Boolean {
        var permissionGranted = true
        if (Build.VERSION.SDK_INT >= 23) {

            permissions.forEach { permission ->
                if (PermissionChecker.checkSelfPermission(appContext, permission) != PackageManager.PERMISSION_GRANTED && permissionGranted) {
                    permissionGranted = false
                }
            }
        }
        return permissionGranted
    }

    @SuppressLint("WrongConstant")
    fun checkAndRequestPermissions(permissions: Array<String>, handleWithDialogs: Boolean, permissionCallback: PermissionCallback) {

        val permissionsToCheck: ArrayList<String> = ArrayList()
        this.permissionCallback = permissionCallback
        this.permissions = permissions
        dialogHandler = handleWithDialogs

        if (permissions.isEmpty()) {
            Log.e("TAG", "Permission List is Empty")
            permissionCallback.onSuccess()
        } else {

            if (Build.VERSION.SDK_INT >= 23) {
                var permissionGranted = true

                permissions.forEach { permission ->
                    if (PermissionChecker.checkSelfPermission(appContext, permission) != PackageManager.PERMISSION_GRANTED) {
                        permissionGranted = false
                        permissionsToCheck.add(permission)
                    }
                }
                if (permissionGranted) {
                    permissionCallback.onSuccess()
                } else {
                    permissionsRequest.value = permissionsToCheck.toTypedArray()
                }
            } else {
                permissionCallback.onSuccess()
            }
        }
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val permissDenied = ArrayList<String>()

            for (i in permissions.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    permissDenied.add(permissions[i])
                }
            }

            if (permissDenied.isNotEmpty()) {
                permissionsRequestRationale.value = permissDenied
            } else {
                permissionCallback?.onSuccess()
            }
        }
    }

    fun onRequestRationaleResult(permisiDenied: ArrayList<String>, externalPermiss: ArrayList<String>) {
        onPermissDenied.clear()
        externalPermissNeed.clear()
        permisiDenied.forEach { onPermissDenied.add(it) }
        externalPermiss.forEach { externalPermissNeed.add(it) }

        if (dialogHandler) {
            handleOnDeniedDialogs()
        } else {
            permissionCallback?.onFail(onPermissDenied.toTypedArray(), externalPermissNeed.toTypedArray())
        }
    }

    private fun handleOnDeniedDialogs() {
        when {
            externalPermissNeed.isNotEmpty() -> {
                openAppSettingsDialog.value = createPermissionDialogMsg(APP_SETTINGS_MSG)
            }
            onPermissDenied.isNotEmpty() -> {
                openRetryDialog.value = createPermissionDialogMsg(RETRY_MSG)
            }
            else -> {
                permissionCallback?.onSuccess()
            }
        }
    }

    fun onRetryDialogPositiveBtnClick() {
        val permissToRequest: ArrayList<String> = ArrayList()
        permissToRequest.addAll(externalPermissNeed)
        permissToRequest.addAll(onPermissDenied)
        permissionsRequest.value = permissToRequest.toTypedArray()
    }

    fun onRetryDialogNegativeBtnClick() {
        permissionCallback?.onFail(onPermissDenied.toTypedArray(), externalPermissNeed.toTypedArray())
    }

    fun onAppSettingsDialogPositiveBtnClick() {
        permissionCallback?.onFail(onPermissDenied.toTypedArray(), externalPermissNeed.toTypedArray())
    }

    fun onAppSettingsDialogNegativeBtnClick() {
        permissionCallback?.onFail(onPermissDenied.toTypedArray(), externalPermissNeed.toTypedArray())
    }

    private fun createPermissionDialogMsg(dialogMsg: Int): String {
        val msg = appContext.getString(R.string.msg_allow_access)
        var listOfPermissions = ""
        val deniedPermiss: ArrayList<String> = ArrayList()
        deniedPermiss.addAll(externalPermissNeed)
        deniedPermiss.addAll(onPermissDenied)

        deniedPermiss.forEach { permiss ->
            var permissTxt: String = permiss.replace("android.permission.", "")
            permissTxt = permissTxt.replace("_", " ")

            listOfPermissions += if (permiss.indexOf("STORAGE") > -1 && listOfPermissions.indexOf("STORAGE") < 0) {
                "\n\u25cf  ${appContext.getString(R.string.tv_storage)}"
            } else if (permiss.indexOf("SMS") > -1 && listOfPermissions.indexOf("SMS") < 0) {
                "\n\u25cf  ${appContext.getString(R.string.tv_sms)}"
            } else if (permiss.indexOf("LOCATION") > -1 && listOfPermissions.indexOf("LOCATION") < 0) {
                "\n\u25cf  ${appContext.getString(R.string.tv_location)}"
            } else {
                "\n\u25cf  $permissTxt"
            }
        }
        var additionalMsg = ""
        if (dialogMsg == RETRY_MSG) additionalMsg = appContext.getString(R.string.msg_permiss_decline)
        if (dialogMsg == APP_SETTINGS_MSG) additionalMsg = appContext.getString(R.string.msg_permiss_required)

        return "$msg\n$listOfPermissions\n\n$additionalMsg"
    }
}