package com.example.template.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
//            mViewModel.onLoginClick(etPIN.text.toString())
            val dialog = CustomAlertDialog()
            dialog.setTitle("Enter your instrument:")
//            dialog.setMessage("asfsdaf sadf asd fadw fasd fasdfgioaef!")

            dialog.setTextInput("Instrument",object: OnTextInputListener{
                override fun getTextInputLayout(textInputLayout: TextInputLayout) {
                    textInputLayout.editText?.addTextChangedListener(object : TextWatcher{
                        override fun afterTextChanged(s: Editable?) {

                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                           Toast.makeText(context,s,Toast.LENGTH_LONG).show()

                        }
                    })
                }
            })
            dialog.setPositiveButton("ok",object : OnButtonClickListener{
                override fun onClick() {
                    dialog.dismiss()
                }
            })
            dialog.show(fragmentManager!!,"a")
        }
    }

    private fun navigateToMainScreen(){
        findNavController().popBackStack()
        findNavController().navigate(R.id.mainFragment)
    }




}
