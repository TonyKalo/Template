package com.example.template.ui.registration_login.registration_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.template.R
import com.example.template.core.base.BaseActivity
import com.example.template.databinding.ActivityRegistrationBinding
import com.example.template.utils.helpers.viewBinding.viewBinding

class RegistrationActivity : BaseActivity<RegistrationActivityViewModel>() {

    private val binding: ActivityRegistrationBinding by viewBinding()
    private val mViewModel by viewModels<RegistrationActivityViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {}
        findNavController(R.id.fhRegistrationMain)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }

    override fun getViewModel(): RegistrationActivityViewModel {
        return mViewModel
    }
}
