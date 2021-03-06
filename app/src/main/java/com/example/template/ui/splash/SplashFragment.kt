package com.example.template.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : BaseFragment<SplashViewModel>() {

    lateinit var splashViewModel: SplashViewModel

    override fun getViewModel(): SplashViewModel {
        activityComponent.inject(this)
        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
        return splashViewModel
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        splashViewModel.loadData()
        splashViewModel.navigateToNextScreen.observe(viewLifecycleOwner, Observer { if(it) navigateToLogin() })
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().popBackStack()



    }

    private fun navigateToLogin(){
        findNavController().navigate(R.id.loginFragment)
    }
}
