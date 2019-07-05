package com.example.template.ui.splash


import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.SchedulerProvider
import javax.inject.Inject

class SplashViewModel @Inject constructor(dataManager: DataManager,schedulerProvider: SchedulerProvider) : BaseViewModel(dataManager,schedulerProvider) {

}