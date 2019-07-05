package com.example.template.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.data.DataManager
import com.example.template.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel @Inject constructor(val dataManager:DataManager, val schedulerProvider: SchedulerProvider,val compositeDisposable: CompositeDisposable) : ViewModel() {


   val isLoading = MutableLiveData<Boolean>()

   fun setLoading(isLoading:Boolean){
      this.isLoading.value=isLoading
   }


}