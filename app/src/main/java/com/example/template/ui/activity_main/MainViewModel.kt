package com.example.template.ui.activity_main

import com.example.template.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    fun test(){
        isLoading.value=true
    }
}