package com.example.template.ui.main_screen.permission_fragment.di

import com.example.template.ui.main_screen.permission_fragment.PermissionFragment
import com.example.template.ui.splash.SplashFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [PermissionModule::class, PermissionBindModule::class])
interface PermissionComponent : AndroidInjector<PermissionFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<PermissionFragment>
}
