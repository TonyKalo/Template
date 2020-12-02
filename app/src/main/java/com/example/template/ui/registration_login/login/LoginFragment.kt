package com.example.template.ui.registration_login.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnMultiChoiceClickListener
import com.example.template.R
import com.example.template.core.base.BaseFragment
import com.example.template.databinding.FragmentLoginBinding
import com.example.template.ui.main_screen.main_activity.MainActivity
import com.example.template.utils.helpers.viewBinding.viewBinding

class LoginFragment : BaseFragment<LoginViewModel>(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val mViewModel by viewModels<LoginViewModel> { viewModelFactory }

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
        mViewModel.toNextScreen.observe(viewLifecycleOwner, { navigateToMainScreen() }) }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            val a = CustomAlertDialog()
            a.setMessage("asdfasdfas")
            a.setTitle("asdasdasd")
            val asd = ArrayList<String>()
            asd.add("asd")
            asd.add("asada asd")

            val ass = ArrayList<Boolean>()
            ass.add(true)
            ass.add(true)

            a.setMultiChoiceItems(asd, ass, object : OnMultiChoiceClickListener {
                override fun onClick(position: Int, isChecked: Boolean) {
                }
            })
            a.show(childFragmentManager, "asdf")
//            mViewModel.onLoginClick(binding.etPIN.text.toString())
        }
    }

    private fun navigateToMainScreen() {
        requireActivity().startActivity(Intent(requireContext(), MainActivity::class.java))
        requireActivity().finish()
    }
}
