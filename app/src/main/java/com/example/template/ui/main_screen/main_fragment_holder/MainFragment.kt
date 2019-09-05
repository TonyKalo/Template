package com.example.template.ui.main_screen.main_fragment_holder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.template.R
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.ui.base.BaseFragment
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : BaseFragment<MainFragmentViewModel>() {

    lateinit var mViewModel: MainFragmentViewModel
    lateinit var sharedViewModel: MainScreenSharedViewModel


    @Inject
    @field:SharedViewModelFactory
    lateinit var sharedViewModelFactory: ViewModelProvider.Factory

    override fun getViewModel(): MainFragmentViewModel {
        activityComponent.inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainFragmentViewModel::class.java)
        sharedViewModel = ViewModelProviders.of(this, sharedViewModelFactory).get(MainScreenSharedViewModel::class.java)
        return mViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavController()
        sharedViewModel.getNavigateToLogin().observe(viewLifecycleOwner, Observer { if(it) navigateToLogin() })
    }

    fun setupNavController(){
        val navController = Navigation.findNavController(requireActivity(), R.id.fhMainFragment)
        var addToBackStack=false
        bnvMain.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                if(item.itemId!=R.id.permissionFragment){
                    if(addToBackStack==false) addToBackStack=true else  navController.popBackStack()
                }else{
                    navController.popBackStack()
                }
                navController.navigate(item.itemId)

                return true
            }
        })
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.loginFragment)
        sharedViewModel.setNavigateToLogin(false)
    }

}
