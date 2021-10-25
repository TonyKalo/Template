package com.example.template.ui.main_screen.picture_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentPictureBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureFragment : BaseFragment<PictureViewModel>(R.layout.fragment_picture) {

    private val binding: FragmentPictureBinding by viewBinding()
    private val mViewModel: PictureViewModel by viewModels()

    override fun getViewModel(): PictureViewModel {
        return mViewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDetails.setOnClickListener { findNavController().navigate(PictureFragmentDirections.actionPictureFragmentToDetailFragment("This is detail txt")) }
    }
}
