package com.example.template.ui.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.alert_dialog.listeners.OnButtonClickListener
import com.cyberslabs.customwidgets.progress_dialog.CustomProgressDialog
import com.example.template.R
import com.example.template.TemplateApp
import com.example.template.di.components.AppComponent
import com.example.template.di.qualifiers.BaseActivityScope
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import com.example.template.ui.splash.SplashFragment
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel> : Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    @SharedViewModelFactory
    lateinit var sharedViewModelFactory: ViewModelProvider.Factory

    @Inject
    @SharedViewModelFactory
    lateinit var sharedViewModelFactory: ViewModelProvider.Factory

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = (requireActivity().application as TemplateApp).getAppComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        resetAppIfIsKilledBySystem()
        viewModel = getViewModel()
        observeAll()
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


     fun showNonCancelableProgress() {
        progressDialog.isCancelable = false
        progressDialog.show(requireActivity().supportFragmentManager, "")
    }

    fun showCancelableProgress() {
        progressDialog.isCancelable = true
        progressDialog.show(requireActivity().supportFragmentManager, "")
    }

    fun hideProgress() {
        if (progressDialog.dialog!!.isShowing) progressDialog.dismiss()
    }

     fun showSnackbar(msg: String) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

     fun requestPermission(permissions: Array<String>) {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions, BaseViewModel.PERMISSION_REQUEST_CODE)
        }
    }

     fun requestPermissionRationale(permissDenied: ArrayList<String>){
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

            viewModel?.onRequestRationaleResult(permDenied,externalPermiss)
        }
    }


    fun openAppSettingsDialog(msg: String) {
        appSettingDialog.setTitle(getString(R.string.title_permiss_required))
        appSettingDialog.setMessage(msg)
        appSettingDialog.isCancelable = false
        appSettingDialog.setPositiveButton(getString(R.string.btn_app_settings), object : OnButtonClickListener {
            override fun onClick() {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", requireContext().packageName, null)
                intent.data = uri
                startActivity(intent)
                viewModel?.onAppSettingsDialogPositiveBtnClick()
                appSettingDialog.dismiss()
            }

        })
        appSettingDialog.setNegativeButton(getString(R.string.btn_cancel),false, object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onAppSettingsDialogNegativeBtnClick()
                appSettingDialog.dismiss()
            }

        })
        appSettingDialog.show(requireActivity().supportFragmentManager, "appSettingDialog")
    }

     fun openRetryDialog(msg: String) {
        retryDialog.setTitle(getString(R.string.title_permiss_decline))
        retryDialog.setMessage(msg)
        retryDialog.isCancelable = false
        retryDialog.setPositiveButton(getString(R.string.btn_retry), object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onRetryDialogPositiveBtnClick()
                retryDialog.dismiss()
            }

        })
        retryDialog.setNegativeButton(getString(R.string.btn_sure),false, object : OnButtonClickListener {
            override fun onClick() {
                viewModel?.onRetryDialogNegativeBtnClick()
                retryDialog.dismiss()
            }

        })
        retryDialog.show(requireActivity().supportFragmentManager, "retryDialog")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        viewModel?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    fun resetAppIfIsKilledBySystem() {

        if (!(this is SplashFragment) && !SplashFragment.isItInit) {
            val intent = Intent(requireContext(), RegistrationActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            System.exit(0)

        }
    }

}