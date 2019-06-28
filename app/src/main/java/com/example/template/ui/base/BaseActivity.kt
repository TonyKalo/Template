package com.example.template.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.template.TemplateApp
import com.example.template.di.components.ActivityComponent
import com.example.template.di.components.DaggerActivityComponent
import com.example.template.di.module.ActivityModule
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseActivity<V:BaseViewModel>: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activityComponent: ActivityComponent
    private var baseViewModel: V? = null
    lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as TemplateApp).getAppComponent())
            .build()

        baseViewModel = getViewModel()


        baseViewModel?.isLoading?.observe(this, Observer { isShowing ->

            if (isShowing) showProgress() else hideProgress()})
    }

    abstract fun getViewModel(): V


    fun showProgress(){
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.show()
    }

    fun hideProgress(){
        progressDialog.dismiss()

    }
}