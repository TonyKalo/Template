package com.example.template.ui.login


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable) {

    private val toNextScreen= MutableLiveData<Boolean>()

    fun getNavigateToNextScreen(): MutableLiveData<Boolean>{
       return toNextScreen
    }

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


    fun onLoginClick(pin:String){
        if(pin.length<1) handleErrorString("Please enter any the pin ")else toNextScreen.value=true
    }


    //  sample for check permissions
//    fun checkPermission (pin:String) {
//
//        val permissions = arrayOf(
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
//            , android.Manifest.permission.SEND_SMS, android.Manifest.permission.CAMERA
//        )
//
//        checkAndRequestPermissions(permissions, true,object : PermissionCallback {
//            override fun onSuccess() {
//                if (pin.equals("12345678"))  toNextScreen.value=true else handleErrorString("Wrong Pin")
//
//            }
//
//            override fun onFail(deniedPermiss: Array<String>, needExternalPermiss: Array<String>) {
//
//            }
//
//        })
//
//    }
}