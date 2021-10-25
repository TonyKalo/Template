package com.example.template.ui.splash

import androidx.lifecycle.viewModelScope
import com.example.template.core.base.BaseViewModel
import com.example.template.core.data.Result
import com.example.template.core.data.Result.Error
import com.example.template.core.data.Result.Success
import com.example.template.core.data.Result.Loading
import com.example.template.ui.splash.data.SplashRepo
import com.example.template.utils.helpers.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repo: SplashRepo) : BaseViewModel() {

    var navigateToNextScreen = SingleLiveEvent<Any>()

    fun loadData() {
        viewModelScope.launch {
            repo.getData().collect {
                when (it) {
                    is Success -> navigateToNextScreen.call()
                    is Error -> handleError(it.exception)
                    is Loading -> {
//                        showLoader(it.isLoading)
                    }
                }
            }
        }
    }
}
