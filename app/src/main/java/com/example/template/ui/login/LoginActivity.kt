package com.example.template.ui.login


import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.template.ui.LoginFragment
import com.example.template.R

import com.example.template.ui.base.BaseActivity


class LoginActivity :  BaseActivity<LoginViewModel>() {



    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = NavHostFragment.create(R.navigation.navigation_graph)

    }

    override fun getViewModel(): LoginViewModel {
        activityComponent.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        return loginViewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }


}
