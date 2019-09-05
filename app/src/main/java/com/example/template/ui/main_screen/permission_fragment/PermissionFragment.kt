package com.example.template.ui.main_screen.permission_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_permission.*


class PermissionFragment : BaseFragment<PermissionViewModel>() {

    lateinit var mViewModel: PermissionViewModel

    override fun getViewModel(): PermissionViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(PermissionViewModel::class.java)
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
        btnPermission.setOnClickListener {
            mViewModel.checkPermissions()
        }
    }

    private fun showMsg(msg:String){
        Toast.makeText(this.context,msg,Toast.LENGTH_SHORT).show()
    }
}
