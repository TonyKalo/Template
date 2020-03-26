package com.example.template.ui.base

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnButtonClickListener
import com.cyberslabs.customwidgets.progress_dialog.CustomProgressDialog
import com.example.template.R
import com.example.template.TemplateApp
import com.example.template.di.components.AppComponent
import com.example.template.di.qualifiers.BaseActivityScope
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity(), BaseViewInterface {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    @BaseActivityScope
    lateinit var progressDialog: CustomProgressDialog
    @Inject
    @BaseActivityScope
    lateinit var retryDialog: CustomAlertDialog
    @Inject
    @BaseActivityScope
    lateinit var appSettingDialog: CustomAlertDialog

    lateinit var appComponent: AppComponent
    private var viewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent = (application as TemplateApp).getAppComponent()
        viewModel = getViewModel()
        observeAll()
    }

    fun setViewModel(viewModel: BaseViewModel) {
        this.viewModel = viewModel
    }

    abstract fun getViewModel(): V

    fun observeAll() {
        observeLoadingNonCancelable()
        observeLoadingCancelable()
        observeErrorHandler()
        observePermissions()
        observePermissionsRationale()
        observeAppSettingsDialog()
        observeRetryDialog()
    }

    private fun observeLoadingNonCancelable() {
        viewModel?.getIsLoadingNonCancelable()
            ?.observe(this, Observer { isShowing -> if (isShowing) showNonCancelableProgress() else hideProgress() })
    }

    private fun observeLoadingCancelable() {
        viewModel?.getIsLoading()
            ?.observe(this, Observer { isShowing -> if (isShowing) showCancelableProgress() else hideProgress() })
    }

    private fun observeErrorHandler() {
        viewModel?.getErrorHandler()?.observe(this, Observer { error ->
            showSnackbar(error)
        })
    }

    private fun observePermissions() {
        viewModel?.getPermissionRequest()?.observe(this, Observer {
            requestPermission(it)
        })
    }

    private fun observePermissionsRationale() {
        viewModel?.getPermissionRequestRationale()?.observe(this, Observer { requestPermissionRationale(it) })
    }

    private fun observeAppSettingsDialog() {
        viewModel?.getAppSettingsRetryDialog()?.observe(this, Observer {
            openAppSettingsDialog(it)
        })
    }

    private fun observeRetryDialog() {
        viewModel?.getRetryDialog()?.observe(this, Observer {
            openRetryDialog(it)
        })
    }

    /**
     * All override methods must be implemented in BaseFragment
     */

    override fun showNonCancelableProgress() {
        progressDialog.isCancelable = false
        progressDialog.show(supportFragmentManager, "")
    }

    override fun showCancelableProgress() {
        progressDialog.isCancelable = true
        progressDialog.show(supportFragmentManager, "")
    }

    override fun hideProgress() {
        if (progressDialog.dialog!!.isShowing) progressDialog.dismiss()
    }

    override fun showSnackbar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun requestPermission(permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, BaseViewModel.PERMISSION_REQUEST_CODE)
        }
    }

    override fun requestPermissionRationale(permissDenied: ArrayList<String>) {
        if (Build.VERSION.SDK_INT >= 23) {
            val permDenied = ArrayList<String>()
            val externalPermiss = ArrayList<String>()

            permissDenied.forEachIndexed { i, s ->
                if (shouldShowRequestPermissionRationale(permissDenied[i])) {
                    permDenied.add(permissDenied[i])
                } else {
                    externalPermiss.add(permissDenied[i])
                }
            }

            viewModel?.onRequestRationaleResult(permDenied, externalPermiss)
        }
    }

    override fun openAppSettingsDialog(msg: String) {
        appSettingDialog.setTitle(getString(R.string.title_permiss_required))
        appSettingDialog.setMessage(msg)
        appSettingDialog.isCancelable = false
        appSettingDialog.setPositiveButton(getString(R.string.btn_app_settings), object : OnButtonClickListener {
            override fun onClick() {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", applicationContext.packageName, null)
                intent.data = uri
                startActivity(intent)
                viewModel?.onAppSettingsDialogPositiveBtnClick()
                appSettingDialog.dismiss()
            }
        })
        appSettingDialog.setNegativeButton(getString(R.string.btn_cancel), false, object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onAppSettingsDialogNegativeBtnClick()
                appSettingDialog.dismiss()
            }
        })
        appSettingDialog.show(supportFragmentManager, "appSettingDialog")
    }

    override fun openRetryDialog(msg: String) {
        retryDialog.setTitle(getString(R.string.title_permiss_decline))
        retryDialog.setMessage(msg)
        retryDialog.isCancelable = false
        retryDialog.setPositiveButton(getString(R.string.btn_retry), object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onRetryDialogPositiveBtnClick()
                retryDialog.dismiss()
            }
        })
        retryDialog.setNegativeButton(getString(R.string.btn_sure), false, object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onRetryDialogNegativeBtnClick()
                retryDialog.dismiss()
            }
        })
        retryDialog.show(supportFragmentManager, "retryDialog")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        @Suppress("IMPLICIT_BOXING_IN_IDENTITY_EQUALS")
        if (ev?.getAction() === MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.getRawX().toInt(), ev.getRawY().toInt())) {
                    v.isFocusableInTouchMode = false
                    v.clearFocus()
                    v.isFocusableInTouchMode = true
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}
