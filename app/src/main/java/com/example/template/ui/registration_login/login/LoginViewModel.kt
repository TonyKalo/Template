package com.example.template.ui.registration_login.login

import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.helpers.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel(dataManager) {

    private val toNextScreen = SingleLiveEvent<Any>()

    fun getNavigateToNextScreen() = toNextScreen

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

    fun onLoginClick(pin: String) {
        if (pin.length <1) handleErrorString("Please enter any the pin ")else toNextScreen.call()
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
