package com.example.template.ui.registration_login.registration_activity


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

import androidx.navigation.findNavController
import com.example.template.R
import com.example.template.data.DataManager

import com.example.template.ui.base.BaseActivity
import javax.inject.Inject


class RegistrationActivity :  BaseActivity<RegistrationActivityViewModel>() {

    @Inject
    lateinit var handler: DataManager
    private val mViewModel by viewModels<RegistrationActivityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fhRegistrationMain)
        Log.e("TAG",handler.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }

    override fun getViewModel(): RegistrationActivityViewModel {
        appComponent.inject(this)
        return mViewModel
    }


}
