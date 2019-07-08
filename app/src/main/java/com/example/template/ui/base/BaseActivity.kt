package com.example.template.ui.base

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
import android.content.Context
import android.view.MotionEvent
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import android.widget.EditText
import androidx.core.app.ActivityCompat
import com.example.template.ui.base.callbacks.PermissionCallback
import com.example.template.ui.base.dialogs.PermissionDialog


abstract class BaseActivity<V:BaseViewModel>: AppCompatActivity(),BaseViewInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var progressDialog: ProgressDialogMain
    @Inject
    lateinit var permissionDialog: PermissionDialog

    lateinit var activityComponent: ActivityComponent
    private var baseViewModel: V? = null
    val PERMISSION_REEQUEST_CODE = 101


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
         observePermissions(viewModel)
    }

    private fun observeLoading(viewModel:BaseViewModel){
        viewModel.isLoading.observe(this, Observer { isShowing -> if (isShowing) showProgress() else hideProgress()})
    }

    private fun observeErrorHandler(viewModel:BaseViewModel){
        viewModel.handleErrorString.observe(this, Observer { error-> showSnackbar(error)

        })
    }

    private fun observePermissions(viewModel:BaseViewModel){
        viewModel.permissionsRequest.observe(this, Observer {
            permissionDialog.permissCallback=it.permissionCallback
            permissionDialog.dialogHandler=it.handleDialog
            permissionDialog.permissions=it.permissions
            permissionDialog.show(supportFragmentManager,"permissDialog")
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

    override fun requestPermission(permissions: Array<String>) {



    }



    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        if (ev?.getAction() === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.getRawX().toInt(), ev.getRawY().toInt())) {
                    v.isFocusableInTouchMode=false
                    v.clearFocus()
                    v.isFocusableInTouchMode=true
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}