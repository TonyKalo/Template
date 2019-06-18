package com.example.template.ui.login


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.template.R
import com.example.template.data.AppDataManager
import com.example.template.data.DataManager
import com.example.template.data.db.AppDatabase
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.module.ActivityModule
import com.example.template.TemplateApp
import com.example.template.data.db.model.Users
import com.example.template.di.components.ActivityComponent

import com.example.template.ui.LoginViewModel
import com.example.template.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class LoginActivity :  BaseActivity<LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.template.R.layout.activity_main)

        activityComponent.inject(this)
        var loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        tv_hello.setOnClickListener { view -> loginViewModel.isLoading.value=true
        Log.e("TAG","click")}



    }
}
