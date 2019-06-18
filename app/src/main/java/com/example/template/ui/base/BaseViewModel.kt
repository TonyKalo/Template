package com.example.template.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

   var isLoading = MutableLiveData<Boolean>()


}