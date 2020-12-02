package com.example.template.ui.main_screen.permission_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentPermissionBinding
import com.example.template.utils.helpers.viewBinding.viewBinding

class PermissionFragment : BaseFragment<PermissionViewModel>(R.layout.fragment_permission) {

    private val binding: FragmentPermissionBinding by viewBinding()
    private val mViewModel by viewModels<PermissionViewModel> { viewModelFactory }

    override fun getViewModel(): PermissionViewModel {
        return mViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setObservers()
    }

    private fun setObservers() {
        showMsgObserver()
    }

    private fun showMsgObserver() {
        mViewModel.msgToShow.observe(viewLifecycleOwner, { msg ->
            showMsg(msg)
        })
    }

    private fun setClickListeners() {
        binding.btnPermission.setOnClickListener { findNavController().navigate(PermissionFragmentDirections.actionPermissionFragmentToDetailFragment2("message to detail fragment")) }
    }

    private fun showMsg(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }
}
