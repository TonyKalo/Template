package com.example.template.ui.main_screen.permission_fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentPermissionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionFragment : BaseFragment<PermissionViewModel>(R.layout.fragment_permission) {

    private val binding: FragmentPermissionBinding by viewBinding()
    private val mViewModel: PermissionViewModel by viewModels()

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
        mViewModel.msgToShow.observe(
            viewLifecycleOwner,
            { msg ->
                showMsg(msg)
            }
        )
    }

    private fun setClickListeners() {
        binding.btnPermission.setOnClickListener { mViewModel.checkPermissions() }
    }

    private fun showMsg(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }
}
