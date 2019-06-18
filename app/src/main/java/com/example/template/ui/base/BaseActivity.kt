package com.example.template.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.template.TemplateApp
import com.example.template.di.components.ActivityComponent
import com.example.template.di.components.DaggerActivityComponent
import com.example.template.di.module.ActivityModule
import com.example.template.ui.LoginViewModel
import android.icu.lang.UCharacter.GraphemeClusterBreak.V



open class BaseActivity<V:BaseViewModel>: AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent
    private val baseViewModel: V? = null
    lateinit var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as TemplateApp).getAppComponent())
            .build()


        baseViewModel?.isLoading?.observe(this, Observer { isShowing ->
            Log.e("TAG",isShowing.toString())
            if (isShowing) showProgress() else hideProgress()})
    }


    fun showProgress(){
        Log.e("TAG","show")
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.show()
    }

    fun hideProgress(){
        progressDialog.dismiss()

    }
}