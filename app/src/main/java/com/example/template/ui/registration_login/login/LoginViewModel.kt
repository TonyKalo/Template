package com.example.template.ui.registration_login.login

import com.example.template.core.base.BaseViewModel
import com.example.template.utils.helpers.SingleLiveEvent
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    val toNextScreen = SingleLiveEvent<Any>()

    fun onLoginClick(pin: String) {
        if (pin.isEmpty()) handleErrorString("Please enter any the pin ") else toNextScreen.call()
    }
}
