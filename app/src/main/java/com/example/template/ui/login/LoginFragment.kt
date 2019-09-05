package com.example.template.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment<LoginViewModel>() {


    lateinit var mViewModel: LoginViewModel


    override fun getViewModel(): LoginViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        return mViewModel
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
        mViewModel.getNavigateToNextScreen().observe(viewLifecycleOwner, Observer {
            if(it)navigateToMainScreen()
        })
    }
    private fun setClickListeners(){
        btnLogin.setOnClickListener {
            mViewModel.onLoginClick(etPIN.text.toString())
        }
    }

    private fun navigateToMainScreen(){
        findNavController().popBackStack()
        findNavController().navigate(R.id.mainFragment)
    }




}
