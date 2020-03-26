package com.example.template.ui.main_screen.picture_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_picture.*

class PictureFragment : BaseFragment<PictureViewModel>() {

    private val mViewModel by viewModels<PictureViewModel> { viewModelFactory }
    private val sViewModel by viewModels<MainScreenSharedViewModel> { sharedViewModelFactory }

    override fun getViewModel(): PictureViewModel {
        appComponent.pictureComponent().create().inject(this)
        return mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDetails.setOnClickListener { findNavController().navigate(PictureFragmentDirections.actionPictureFragmentToDetailFragment("This is detail txt")) }
}
}
