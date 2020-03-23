package com.example.template.ui.registration_login.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnButtonClickListener
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnTextInputListener
import com.example.template.R
import com.example.template.ui.base.BaseFragment
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment<LoginViewModel>() {


    private val mViewModel by viewModels<LoginViewModel> { viewModelFactory }


    override fun getViewModel(): LoginViewModel {
        appComponent.loginComponent().create().inject(this)
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


    private fun setObservers() {
        navigateToNextScreenObserver()
    }

    private fun navigateToNextScreenObserver() {
        mViewModel.getNavigateToNextScreen().observe(viewLifecycleOwner, Observer { navigateToMainScreen() })
    }

    private fun setClickListeners() {
        btnLogin.setOnClickListener {
            mViewModel.onLoginClick(etPIN.text.toString())

        }
    }

    private fun navigateToMainScreen() {
//        findNavController().popBackStack()
//        findNavController().navigate(R.id.mainFragment)
    }


}
