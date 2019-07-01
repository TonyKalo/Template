package com.example.template.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.template.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navController = NavHostFragment.create(R.navigation.bottom_navigation)
//        val navController = Navigation.
//            findNavController(this.requireActivity(),R.id.bottom_navigation)

//        bottom_navigation.setupWithNavController(findNavController())
//        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
//
//        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
