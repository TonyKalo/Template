package com.example.template.ui.base.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.DialogFragment
import com.example.template.R
import com.example.template.ui.base.callbacks.PermissionCallback
import javax.inject.Inject


class PermissionDialog @Inject constructor() : DialogFragment() {

    val PERMISSION_REQUEST_CODE = 101

    var permissCallback: PermissionCallback? = null
    var permissions: Array<String>? = null
    var dialogHandler: Boolean = true
    private var externalPermissNeed: ArrayList<String> = ArrayList()
    private var onPermissDenied: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermissions()
    }

    private fun checkPermissions() {
        var permissionsToCheck: ArrayList<String> = ArrayList()

        if (permissions == null || permissions?.size!! < 1) {
            Log.e("TAG", "Permission List is Empty")
            permissCallback?.onSuccess()
        } else {

            if (Build.VERSION.SDK_INT >= 23) {
                var permissionGranted: Boolean = true

                permissions!!.forEach { permission ->
                    if (checkSelfPermission(context!!, permission) != PackageManager.PERMISSION_GRANTED) {
                        permissionGranted = false
                        permissionsToCheck.add(permission)
                    }
                }
                if (permissionGranted) {
                    permissCallback?.onSuccess()
                    dismiss()
                } else {
                    requestPermiss(permissionsToCheck.toTypedArray()) }

            } else {
                permissCallback?.onSuccess()
            }
        }
    }


    private fun requestPermiss(permissions: Array<String>) {
        requestPermissions(permissions, PERMISSION_REQUEST_CODE)

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            val permissDenied = ArrayList<String>()

            for (i in 0..permissions.size - 1) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    permissDenied.add(permissions[i])
                }
            }


            if (!permissDenied.isEmpty()) {
                onPermissDenied.clear()
                externalPermissNeed.clear()

                for (i in 0..permissDenied.size - 1) {
                    if (shouldShowRequestPermissionRationale(permissDenied.get(i))) {
                        onPermissDenied.add(permissDenied.get(i))
                    } else {
                        externalPermissNeed.add(permissDenied.get(i));
                    }
                }

                if (dialogHandler) {
                    handleOnDeniedDialogs()
                } else {
                    permissCallback?.onFail(onPermissDenied.toTypedArray(), externalPermissNeed.toTypedArray())
                    dismiss()
                }


            } else {
                permissCallback?.onSuccess()
                dismiss()
            }


        }
    }

    private fun handleOnDeniedDialogs() {
        if (!externalPermissNeed.isEmpty()) {
            openAppSettingsDialog()
        } else if (!onPermissDenied.isEmpty()) {
            openRetryDialog()
        } else {
            permissCallback?.onSuccess()
        }

    }

    private fun openRetryDialog() {

        AlertDialog.Builder(context,R.style.Main_Dialog_Theme)
            .setTitle(getString(R.string.title_permiss_decline))
            .setMessage(createDialogMsg())
            .setCancelable(false)
            .setPositiveButton(getString(R.string.btn_retry)){ dialog, which ->
                val permissToRequest:ArrayList<String> = ArrayList()
                permissToRequest.addAll(externalPermissNeed)
                permissToRequest.addAll(onPermissDenied)
                requestPermiss(permissToRequest.toTypedArray())
            }
            .setNegativeButton(getString(R.string.btn_cancel)){ dialog, which ->
                permissCallback?.onFail(onPermissDenied.toTypedArray(),externalPermissNeed.toTypedArray())
                dismiss()
                this.dismiss()
            }.create().show()
    }

    private fun openAppSettingsDialog() {
        AlertDialog.Builder(context,R.style.Main_Dialog_Theme)
            .setTitle(getString(R.string.title_permiss_required))
            .setMessage(createDialogMsg())
            .setCancelable(false)
            .setPositiveButton(getString(R.string.btn_app_settings)){ dialog, which ->
               val intent = Intent()
                intent.action= Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package",context?.applicationContext?.packageName,null)
                intent.setData(uri)
                context?.startActivity(intent)
                permissCallback?.onFail(onPermissDenied.toTypedArray(),externalPermissNeed.toTypedArray())
                dismiss()
            }
            .setNegativeButton(R.string.btn_cancel){dialog, which ->
                permissCallback?.onFail(onPermissDenied.toTypedArray(),externalPermissNeed.toTypedArray())
                dismiss()
            }.create().show()
    }


    private fun createDialogMsg(): String {
        val msg = getString(R.string.msg_allow_access)
        var listOfPermissions = ""
        val deniedPermiss:ArrayList<String> = ArrayList()
        deniedPermiss.addAll(externalPermissNeed)
        deniedPermiss.addAll(onPermissDenied)

        deniedPermiss.forEach {permiss->
            var permissTxt = permiss
            permissTxt = permiss.replace("android.permission.", "")
            permissTxt =permissTxt.replace("_"," ")

            if (permiss.indexOf("STORAGE")>-1 && listOfPermissions.indexOf("STORAGE")<0) {
                listOfPermissions += "\n\u25cf  ${getString(R.string.tv_storage)}"
            }else if(permiss.indexOf("SMS")>-1 && listOfPermissions.indexOf("SMS")<0) {
                listOfPermissions += "\n\u25cf  ${getString(R.string.tv_sms)}"
            }else{
                listOfPermissions += "\n\u25cf  $permissTxt"
            }
        }

        return "$msg\n$listOfPermissions"

    }

}