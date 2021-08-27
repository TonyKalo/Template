package com.example.template.core.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseViewModel> : Fragment(), BaseViewInterface {

    lateinit var baseActivity: BaseActivity<*>
    private var baseViewModel: V? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            baseActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        baseViewModel = getViewModel()
        baseActivity.setViewModel(baseViewModel!!)
        baseActivity.observeAll()
    }

    abstract fun getViewModel(): V

    override fun showCancelableProgress() {
        baseActivity.showCancelableProgress()
    }

    override fun showNonCancelableProgress() {
        baseActivity.showNonCancelableProgress()
    }

    override fun hideProgress() {
        baseActivity.hideProgress()
    }

    override fun showSnackbar(msg: String) {
        baseActivity.showSnackbar(msg)
    }

    override fun requestPermission(permissions: Array<String>) {
        baseActivity.requestPermission(permissions)
    }

    override fun requestPermissionRationale(permission: ArrayList<String>) {
        return baseActivity.requestPermissionRationale(permission)
    }

    override fun openRetryDialog(msg: String) {
        return baseActivity.openRetryDialog(msg)
    }

    override fun openAppSettingsDialog(msg: String) {
        return baseActivity.openAppSettingsDialog(msg)
    }
}
