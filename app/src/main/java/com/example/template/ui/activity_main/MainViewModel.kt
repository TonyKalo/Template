package com.example.template.ui.activity_main

import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.SchedulerProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(dataManager: DataManager,schedulerProvider: SchedulerProvider) : BaseViewModel(dataManager,schedulerProvider) {

    fun test(){
        isLoading.value=true
    }
}