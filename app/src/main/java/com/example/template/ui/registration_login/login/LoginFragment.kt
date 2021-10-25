package com.example.template.ui.registration_login.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentLoginBinding
import com.example.template.ui.main_screen.main_activity.MainActivity
import com.example.template.utils.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel>(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val mViewModel: LoginViewModel by viewModels()

    override fun getViewModel(): LoginViewModel {
        return mViewModel
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
        binding.btnLogin.setOnClickListener {
            mViewModel.onLoginClick(binding.etPIN.text.toString())
        }
    }

    private fun navigateToMainScreen() {
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }
}
