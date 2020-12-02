package com.example.template.core.base

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.template.core.di.components.AppComponent
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel> constructor(layout: Int) : DaggerFragment(layout), BaseViewInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var activity: BaseActivity<*>? = null
    lateinit var appComponent: AppComponent
    private var baseViewModel: V? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseViewModel = getViewModel()
        activity?.setViewModel(baseViewModel!!)
        activity?.observeAll()
    }

    abstract fun getViewModel(): V

    override fun showCancelableProgress() {
        activity?.showCancelableProgress()
    }

    override fun showNonCancelableProgress() {
        activity?.showNonCancelableProgress()
    }

    override fun hideProgress() {
        activity?.hideProgress()
    }

    override fun showSnackbar(msg: String) {
        activity?.showSnackbar(msg)
    }

    override fun requestPermission(permissions: Array<String>) {
        activity?.requestPermission(permissions)
    }

    override fun requestPermissionRationale(permission: ArrayList<String>): Unit? {
        return activity?.requestPermissionRationale(permission)
    }

    override fun openRetryDialog(msg: String): Unit? {
        return activity?.openRetryDialog(msg)
    }

    override fun openAppSettingsDialog(msg: String): Unit? {
        return activity?.openAppSettingsDialog(msg)
    }
}