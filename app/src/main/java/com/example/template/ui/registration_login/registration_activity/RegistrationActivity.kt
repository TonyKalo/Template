package com.example.template.ui.registration_login.registration_activity


import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

import androidx.navigation.findNavController
import com.example.template.R

import com.example.template.ui.base.BaseActivity


class RegistrationActivity :  BaseActivity<RegistrationActivityViewModel>() {

    private val mViewModel by viewModels<RegistrationActivityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        findNavController(R.id.fhRegistrationMain)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }

    override fun getViewModel(): RegistrationActivityViewModel {
        appComponent.inject(this)
        return mViewModel
    }


}
