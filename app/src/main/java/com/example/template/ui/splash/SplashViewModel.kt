package com.example.template.ui.splash


import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable) {

    var navigateToNextScreen = MutableLiveData<Boolean>()

    fun loadData(){

        compositeDisposable.addAll(dataManager.getApi().getCurrencies().delaySubscription(3,TimeUnit.SECONDS)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({

                navigateToNextScreen.value=true

            },{
                navigateToNextScreen.value=true

            }))
    }

}