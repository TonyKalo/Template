package com.example.template.ui.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import javax.inject.Inject

class SplashFragment : BaseFragment<SplashViewModel>() {

    companion object {
        var isItInit = false
    }
    private val mViewModel by viewModels<SplashViewModel> { viewModelFactory }

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
