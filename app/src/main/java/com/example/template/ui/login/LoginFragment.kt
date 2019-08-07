package com.example.template.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cyberslabs.customalertdialog.CustomAlertDialog
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment<LoginViewModel>() {


    lateinit var loginViewModel: LoginViewModel


    override fun getViewModel(): LoginViewModel {
        activityComponent.inject(this)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        return loginViewModel
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         setClickListeners()
         setObservers()

    }


    private fun setObservers(){
     navigateToNextScreenObserver()
    }

    private fun navigateToNextScreenObserver(){
        loginViewModel.getNavigateToNextScreen().observe(viewLifecycleOwner, Observer {
            if(it)navigateToMainScreen()
        })
    }
    private fun setClickListeners(){
        btnLogin.setOnClickListener {
//            loginViewModel.checkPermission(etPIN.text.toString())
            var dialog = CustomAlertDialog()
            dialog.show(fragmentManager,"aaa")
        }
    }

    private fun navigateToMainScreen(){
        findNavController().popBackStack()
        findNavController().navigate(R.id.mainFragment)
    }




}
