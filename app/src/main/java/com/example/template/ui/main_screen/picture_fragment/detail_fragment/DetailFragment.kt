package com.example.template.ui.main_screen.picture_fragment.detail_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_picture.*

class DetailFragment : BaseFragment<DetailViewModel>() {

    private val mViewModel by viewModels<DetailViewModel> { viewModelFactory }

    override fun getViewModel(): DetailViewModel {
        appComponent.detailComponent().create().inject(this)
        return mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlbFragmentDetail.setNavigationOnClickListener { findNavController().navigateUp() }
        tvDetail.text = DetailFragmentArgs.fromBundle(requireArguments()).detail
    }
}
