package com.example.template.ui.main_screen.picture_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.login.LoginViewModel


class PictureFragment :  BaseFragment<PictureViewModel>() {


    lateinit var mViewModel: PictureViewModel


    override fun getViewModel(): PictureViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PictureViewModel::class.java)
        return  mViewModel
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }



}
