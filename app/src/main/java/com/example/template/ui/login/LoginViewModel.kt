package com.example.template.ui.login


import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.SchedulerProvider
import javax.inject.Inject

class LoginViewModel @Inject constructor(dataManager: DataManager,schedulerProvider: SchedulerProvider) : BaseViewModel(dataManager,schedulerProvider) {

}