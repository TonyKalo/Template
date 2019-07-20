package com.example.template.ui.main_screen.permission_fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.ui.base.callbacks.PermissionCallback
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PermissionViewModel  @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable) {

    private val msgToShow= MutableLiveData<String>()

    fun getMsgToShow(): MutableLiveData<String> {
        return msgToShow
    }

    fun checkPermissions(){
        val permissions = arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            , android.Manifest.permission.SEND_SMS, android.Manifest.permission.CAMERA
        )

        checkAndRequestPermissions(permissions, true,object : PermissionCallback {
            override fun onSuccess() {
                msgToShow.value="All permission granted"

            }

            override fun onFail(deniedPermiss: Array<String>, needExternalPermiss: Array<String>) {

            }

        })
    }
}