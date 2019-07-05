package com.example.template.ui.login


import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(dataManager: DataManager,schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable)
    : BaseViewModel(dataManager,schedulerProvider,compositeDisposable) {

}