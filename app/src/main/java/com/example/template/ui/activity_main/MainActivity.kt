package com.example.template.ui.activity_main


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.template.R

import com.example.template.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :  BaseActivity<MainViewModel>() {



    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       findNavController(R.id.fhMain)

    }

    override fun getViewModel(): MainViewModel {
        activityComponent.inject(this)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhMain).navigateUp()
    }




}
