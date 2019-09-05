package com.example.template.ui.main_screen.permission_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.template.R
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import kotlinx.android.synthetic.main.fragment_permission.*
import javax.inject.Inject


class PermissionFragment : BaseFragment<PermissionViewModel>() {

    lateinit var mViewModel: PermissionViewModel
    lateinit var sharedViewModel: MainScreenSharedViewModel


    @Inject
    @field:SharedViewModelFactory
    lateinit var sharedViewModelFactory: ViewModelProvider.Factory



    override fun getViewModel(): PermissionViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PermissionViewModel::class.java)
        sharedViewModel= ViewModelProviders.of(this, sharedViewModelFactory).get(MainScreenSharedViewModel::class.java)
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

    private fun setObservers(){
        showMsgObserver()
    }

    private fun showMsgObserver(){
        mViewModel.getMsgToShow().observe(viewLifecycleOwner, Observer { msg->
            showMsg(msg)
        })
    }

    private fun setClickListeners(){
        btnPermission.setOnClickListener { mViewModel.checkPermissions() }
        tlbPermissionFragment.setOnClickListener({ sharedViewModel.setNavigateToLogin(true) })
    }

    private fun showMsg(msg:String){
        Toast.makeText(this.context,msg,Toast.LENGTH_SHORT).show()
    }
}
