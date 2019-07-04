package com.example.template.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.activity_main.MainViewModel
import com.example.template.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*


class LoginFragment : BaseFragment<LoginViewModel>() {


    lateinit var loginViewModel: LoginViewModel


    override fun getViewModel(): LoginViewModel {
        activityComponent.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        return loginViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().popBackStack()



        btnLogin.setOnClickListener {
            if(etPIN.text.toString().trim().equals("12345678")) findNavController().navigate(R.id.mainFragment) else Toast.makeText(activity,"Wrong PIN",Toast.LENGTH_SHORT).show()
        }

    }




}
