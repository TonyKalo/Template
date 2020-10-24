package com.example.template.ui.main_screen.picture_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.ui.main_screen.main_activity.MainActivityViewModel
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel
import kotlinx.android.synthetic.main.fragment_picture.*

class PictureFragment : BaseFragment<PictureViewModel>() {

    private val mViewModel by viewModels<PictureViewModel> { viewModelFactory }

    override fun getViewModel(): PictureViewModel {
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
