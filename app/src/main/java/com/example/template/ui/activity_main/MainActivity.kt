package com.example.template.ui.activity_main


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.template.R

import com.example.template.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity :  BaseActivity<MainViewModel>() {



    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       findNavController(R.id.fhMain)

    }

    override fun getViewModel(): MainViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        return mViewModel
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fhMain).navigateUp()
    }




}
