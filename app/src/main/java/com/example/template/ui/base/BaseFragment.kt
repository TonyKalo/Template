package com.example.template.ui.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.template.TemplateApp
import com.example.template.di.components.ActivityComponent
import com.example.template.di.components.DaggerActivityComponent
import com.example.template.di.module.ActivityModule
import com.example.template.di.qualifiers.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<V:BaseViewModel>:Fragment(),BaseViewInterface{

    @Inject
    @field:ViewModelFactory
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activity: BaseActivity<*>
    lateinit var activityComponent: ActivityComponent
    private var baseViewModel: V? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity<*>){
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(activity))
            .appComponent((activity.application as TemplateApp).getAppComponent())
            .build()

        baseViewModel = getViewModel()
        activity.setViewModel(baseViewModel!!)
        activity.observeAll()

    }

    abstract fun getViewModel(): V


     override fun showCancelableProgress() {
         activity.showCancelableProgress()
     }

     override fun showNonCancelableProgress() {
         activity.showNonCancelableProgress()
     }

    override fun hideProgress() {
        activity.hideProgress()
    }

    override fun showSnackbar(msg: String) {
        activity.showSnackbar(msg)
    }

    override fun requestPermission(permissions: Array<String>) {
        activity.requestPermission(permissions)
    }

    override fun requestPermissionRationale(permission : ArrayList<String>) {
        return activity.requestPermissionRationale(permission)
    }

    override fun openRetryDialog(msg: String) {
        return activity.openRetryDialog(msg)
    }

    override fun openAppSettingsDialog(msg: String) {
        return activity.openAppSettingsDialog(msg)
    }
}