package com.example.template.ui.main_screen.permission_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.template.R
import com.example.template.core.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_permission.*

@AndroidEntryPoint
class PermissionFragment : BaseFragment<PermissionViewModel>() {

    private val mViewModel: PermissionViewModel by viewModels()

    override fun getViewModel(): PermissionViewModel {
        return mViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_permission, container, false)
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
        btnPermission.setOnClickListener { mViewModel.checkPermissions() }
    }

    private fun showMsg(msg: String) {
        Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show()
    }
}
