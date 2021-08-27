package com.example.template.ui.registration_login.registration_activity

import com.example.template.core.base.BaseViewModel
import javax.inject.Inject

class RegistrationActivityViewModel @Inject constructor() : BaseViewModel() {

    fun showLoadingMessage() {
        handleErrorString("Loading")
    }
}
