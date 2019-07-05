package com.example.template.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.di.components.ActivityComponent
import javax.inject.Inject

abstract class BaseFragment<V:BaseViewModel>:Fragment(),BaseViewInterface{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activity: BaseActivity<*>
    lateinit var activityComponent: ActivityComponent
    private var baseViewModel: V? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseActivity<*>){
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent=activity.activityComponent
        baseViewModel = getViewModel()
        activity.observeAll(baseViewModel!!)

    }

    abstract fun getViewModel(): V


    override fun showProgress() {
       activity.showProgress()
    }

    override fun hideProgress() {
        activity.hideProgress()
    }

    override fun showSnackbar(msg: String) {
        activity.showSnackbar(msg)
    }




}