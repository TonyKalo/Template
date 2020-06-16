package com.example.template.ui.registration_login.registration_activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.template.R
import com.example.template.core.base.BaseActivity

class RegistrationActivity : BaseActivity<RegistrationActivityViewModel>() {

    private val mViewModel by viewModels<RegistrationActivityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Log.e("TAG", mViewModel.toString())
        findNavController(R.id.fhRegistrationMain)
    }

    override fun onResume() {
        super.onResume()
        Log.e("TAG", mViewModel.toString())
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }

    override fun getViewModel(): RegistrationActivityViewModel {
        return mViewModel
    }
}
