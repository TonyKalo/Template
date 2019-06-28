package com.example.template.ui.login


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.example.template.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class LoginActivity :  BaseActivity<LoginViewModel>() {


    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.template.R.layout.activity_main)


        tv_hello.setOnClickListener { view -> loginViewModel.test()
        Log.e("TAG","click")}



    }

    override fun getViewModel(): LoginViewModel {
        activityComponent.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        return loginViewModel
    }
}
