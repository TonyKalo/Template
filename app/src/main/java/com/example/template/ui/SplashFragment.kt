package com.example.template.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.HandlerCompat.postDelayed
import androidx.navigation.fragment.findNavController
import com.example.template.R
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().popBackStack()

        Handler().postDelayed({
            findNavController().navigate(R.id.loginFragment)
        }, 3000)







    }
}
