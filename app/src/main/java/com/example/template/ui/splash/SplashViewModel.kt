package com.example.template.ui.splash


import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SplashViewModel @Inject constructor(dataManager: DataManager,schedulerProvider: SchedulerProvider,compositeDisposable: CompositeDisposable)
    : BaseViewModel(dataManager,schedulerProvider,compositeDisposable) {

//    fun sample (){
//
//        compositeDisposable.add(dataManager.getApi().getCurrencies()
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//            .subscribe ({
//                    response ->
//                //success
//            },{
//                error->
//
//            }))
//    }


}