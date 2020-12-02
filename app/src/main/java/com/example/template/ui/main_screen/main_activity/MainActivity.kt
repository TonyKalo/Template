package com.example.template.ui.main_screen.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.template.R
import com.example.template.core.base.BaseActivity
import com.example.template.databinding.ActivityMainBinding
import com.example.template.utils.extensions.makeGone
import com.example.template.utils.extensions.makeVisible
import com.example.template.utils.helpers.setupWithNavController
import com.example.template.utils.helpers.viewBinding.viewBinding

class MainActivity : BaseActivity<MainActivityViewModel>(), NavController.OnDestinationChangedListener {

    private val binding: ActivityMainBinding by viewBinding()
    private val mViewModel by viewModels<MainActivityViewModel> { viewModelFactory }
    private var currentNavController: LiveData<NavController>? = null

    override fun getViewModel(): MainActivityViewModel {
        return mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.permission_graph, R.navigation.picture_graph)

        val controller = binding.bnvMain.setupWithNavController(navGraphIds = navGraphIds, fragmentManager = supportFragmentManager,
            containerId = R.id.fhMain, intent = intent)

        controller.observe(this, {
            it.removeOnDestinationChangedListener(this)
            it.addOnDestinationChangedListener(this)
        })

        currentNavController = controller
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        hideBottomNavigation(destination)
    }

    private fun hideBottomNavigation(destination: NavDestination) {
        if (destination.id == R.id.permissionFragment || destination.id == R.id.pictureFragment) {
            binding.bnvMain.makeVisible()
        } else binding.bnvMain.makeGone()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
