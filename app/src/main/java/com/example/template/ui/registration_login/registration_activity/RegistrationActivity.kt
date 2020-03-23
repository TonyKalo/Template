package com.example.template.ui.registration_login.registration_activity


import android.os.Bundle
import androidx.navigation.findNavController
import com.example.template.R

import com.example.template.ui.base.BaseActivity


class RegistrationActivity :  BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.fhRegistrationMain)

    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }




}
