package com.example.template.ui.registration_login.registration_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.template.utils.viewBinding.viewBinding
import com.example.template.R
import com.example.template.core.base.BaseActivity
import com.example.template.databinding.ActivityRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationActivity : BaseActivity<RegistrationActivityViewModel>() {

    private val binding: ActivityRegistrationBinding by viewBinding()
    private val mViewModel: RegistrationActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        findNavController(R.id.fhRegistrationMain)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhRegistrationMain).navigateUp()
    }

    override fun getViewModel(): RegistrationActivityViewModel {
        return mViewModel
    }
}
