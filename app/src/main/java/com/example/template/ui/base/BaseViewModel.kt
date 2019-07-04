package com.example.template.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

   val isLoading = MutableLiveData<Boolean>()

   fun setLoading(isLoading:Boolean){
      this.isLoading.value=isLoading
   }


}