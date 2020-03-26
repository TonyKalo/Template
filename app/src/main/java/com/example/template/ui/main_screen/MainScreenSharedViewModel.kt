package com.example.template.ui.main_screen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainScreenSharedViewModel @Inject constructor(appContext: Context) : ViewModel() {

    private val navigateToLogin = MutableLiveData<Boolean>()

    fun NavigateToLogin() {
        navigateToLogin.value = true
    }

    fun getNavigateToLogin(): MutableLiveData<Boolean> {
        return navigateToLogin
    }

    fun setNavigateToLogin(toLogin: Boolean) {
        navigateToLogin.value = toLogin
    }
}
