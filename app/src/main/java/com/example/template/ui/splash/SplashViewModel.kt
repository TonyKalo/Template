package com.example.template.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.template.core.base.BaseViewModel
import com.example.template.core.data.Result.Error
import com.example.template.core.data.Result.Success
import com.example.template.ui.splash.data.SplashRepo
import com.example.template.utils.helpers.SingleLiveEvent
import javax.inject.Inject
import kotlinx.coroutines.launch

class SplashViewModel @Inject constructor(private val repo: SplashRepo) : BaseViewModel() {

    var navigateToNextScreen = SingleLiveEvent<Any>()

    fun loadData() {
        viewModelScope.launch {
            repo.getData().let {
                when (it) {
                    is Success -> navigateToNextScreen.call()
                    is Error -> handleError(it.exception)
                }
            }
        }
    }
}
