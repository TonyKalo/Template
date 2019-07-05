package com.example.template.ui.base

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor(@AppContext appContext:Context,val dataManager:DataManager, val schedulerProvider: SchedulerProvider, val compositeDisposable: CompositeDisposable) : ViewModel() {


   val isLoading = MutableLiveData<Boolean>()
   private val handleError= MutableLiveData<Throwable>()

//    fun handleError(e: Throwable){
//        if(isNetworkConnected){
//
//        }
//
//    }


}