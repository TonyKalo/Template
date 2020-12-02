package com.example.template.ui.main_screen.picture_fragment.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentDetailBinding
import com.example.template.utils.helpers.viewBinding.viewBinding

class DetailFragment : BaseFragment<DetailViewModel>(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding()
    private val mViewModel by viewModels<DetailViewModel> { viewModelFactory }

    override fun getViewModel(): DetailViewModel {
        return mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tlbFragmentDetail.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.tvDetail.text = DetailFragmentArgs.fromBundle(requireArguments()).detail
    }
}
