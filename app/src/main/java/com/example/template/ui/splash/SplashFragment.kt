package com.example.template.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentSplashBinding
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel
import com.example.template.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {

    companion object {
        var isItInit = false
    }
    private val binding: FragmentSplashBinding by viewBinding()
    private val mViewModel: SplashViewModel by viewModels()
    private val activityViewModel: RegistrationActivityViewModel by activityViewModels()

    override fun getViewModel(): SplashViewModel {
        return mViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        isItInit = true
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.loadData()
        activityViewModel.showLoadingMessage()
        mViewModel.navigateToNextScreen.observe(viewLifecycleOwner, { navigateToLogin() })
        findNavController().popBackStack()
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.loginFragment)
    }
}
