package com.example.template.ui.login


import android.content.Context
import android.util.Log
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.ui.base.callbacks.PermissionCallback
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable) {

//        fun sample (){
//
//        compositeDisposable.add(dataManager.getApi().getCurrencies()
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//            .subscribe ({
//                    response ->
//                //success
//            },{
//                error->
//                Log.e("TAG",error.localizedMessage)
//                handleError(error)
//            }))
//    }

    fun sample2 () {

        val permissions = arrayOf(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            , android.Manifest.permission.SEND_SMS
        )

        checkAndRequestPermissions(permissions, object : PermissionCallback {
            override fun onSuccess() {
               Log.e("TAG","success")
            }

            override fun onFail() {
                Log.e("TAG","fail")
            }

        })
    }
}