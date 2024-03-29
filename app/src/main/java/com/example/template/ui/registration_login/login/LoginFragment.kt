package com.example.template.ui.registration_login.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.ui.main_screen.main_activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>() {

    private val mViewModel: LoginViewModel by viewModels()

    override fun getViewModel(): LoginViewModel {
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
        mViewModel.toNextScreen.observe(viewLifecycleOwner, { navigateToMainScreen() })
    }

    private fun setClickListeners() {
        btnLogin.setOnClickListener {
            mViewModel.onLoginClick(etPIN.text.toString())
        }
    }

    private fun navigateToMainScreen() {
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }
}
