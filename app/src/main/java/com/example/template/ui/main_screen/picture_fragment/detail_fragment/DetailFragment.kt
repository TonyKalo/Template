package com.example.template.ui.main_screen.picture_fragment.detail_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentDetailBinding
import com.example.template.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel>(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private val mViewModel: DetailViewModel by viewModels()

    override fun getViewModel(): DetailViewModel {
        return mViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tlbFragmentDetail.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.tvDetail.text = DetailFragmentArgs.fromBundle(requireArguments()).detail
    }
}
