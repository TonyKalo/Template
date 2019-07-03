package com.example.template.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.template.R
import kotlinx.android.synthetic.main.fragment_login.*
import kotlin.math.log


class LoginFragment : Fragment() {


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
