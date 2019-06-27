package com.example.template.ui.login

import androidx.lifecycle.ViewModel
import com.example.template.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun test(){
        isLoading.value=true
    }
}