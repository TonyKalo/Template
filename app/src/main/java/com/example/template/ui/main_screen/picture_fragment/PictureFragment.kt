package com.example.template.ui.main_screen.picture_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.template.R
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import kotlinx.android.synthetic.main.fragment_picture.*
import javax.inject.Inject


class PictureFragment :  BaseFragment<PictureViewModel>() {


    lateinit var mViewModel: PictureViewModel
    lateinit var sharedViewModel: MainScreenSharedViewModel


    @Inject
    @field:SharedViewModelFactory
    lateinit var sharedViewModelFactory: ViewModelProvider.Factory


    override fun getViewModel(): PictureViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PictureViewModel::class.java)
        sharedViewModel= ViewModelProviders.of(this, sharedViewModelFactory).get(MainScreenSharedViewModel::class.java)
        return  mViewModel
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tlbFragmentPicture.setOnClickListener({ sharedViewModel.setNavigateToLogin(true) })
    }
}
