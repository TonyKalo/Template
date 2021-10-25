package com.example.template.ui.registration_login.registration_activity

import com.example.template.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationActivityViewModel @Inject constructor() : BaseViewModel() {

    fun showLoadingMessage() {
        handleErrorString("Loading")
    }
}
