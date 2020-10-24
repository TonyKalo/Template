package com.example.template.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.ui.main_screen.main_activity.MainActivityViewModel
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel

class SplashFragment : BaseFragment<SplashViewModel>() {

    companion object {
        var isItInit = false
    }
    private val mViewModel by viewModels<SplashViewModel> { viewModelFactory }
    private val activityViewModel by lazy { ViewModelProvider(requireActivity()).get(RegistrationActivityViewModel::class.java) }

    override fun getViewModel(): SplashViewModel {
        return mViewModel
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        isItInit = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewModel.loadData()
        mViewModel.navigateToNextScreen.observe(viewLifecycleOwner, Observer { navigateToLogin() })
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().popBackStack()
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.loginFragment)
    }
}
