package com.example.template.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.example.template.di.components.ActivityComponent
import com.example.template.ui.base.callbacks.PermissionCallback
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

     override fun requestPermissionRationale(permission: String): Boolean {
         return activity.requestPermissionRationale(permission)
     }

    override fun openRetryDialog(msg: String) {
        return activity.openRetryDialog(msg)
    }

    override fun openAppSettingsDialog(msg: String) {
        return activity.openAppSettingsDialog(msg)
    }
}