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
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.template.ui.base.dialogs.ProgressDialogMain
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import android.R.id.message




abstract class BaseActivity<V:BaseViewModel>: AppCompatActivity(),BaseViewInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var progressDialog: ProgressDialogMain

    lateinit var activityComponent: ActivityComponent
    private var baseViewModel: V? = null


    var test: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as TemplateApp).getAppComponent())
            .build()

        baseViewModel = getViewModel()
        observeAll(baseViewModel!!)

    }

    abstract fun getViewModel(): V

     fun observeAll(viewModel:BaseViewModel){
         observeLoading(viewModel)
         observeErrorHandler(viewModel)
    }

    fun observeLoading(viewModel:BaseViewModel){
        viewModel.isLoading.observe(this, Observer { isShowing -> if (isShowing) showProgress() else hideProgress()})
    }

    fun observeErrorHandler(viewModel:BaseViewModel){
        viewModel.handleErrorString.observe(this, Observer { error-> showSnackbar(error)

        })
    }

    /**
     * All override methods must be implemented in BaseFragment
     */

    override fun showProgress(){

        progressDialog.show(supportFragmentManager,"")
    }

    override fun hideProgress(){
        if(progressDialog.dialog.isShowing) progressDialog.dismiss()
    }

    override fun showSnackbar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }
}