package com.example.template.ui.main_screen.permission_fragment.di

import com.example.template.ui.main_screen.permission_fragment.PermissionFragment
import dagger.Subcomponent

@Subcomponent(modules = [PermissionModule::class, PermissionBindModule::class])
interface PermissionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PermissionComponent
    }

    fun inject(fragment: PermissionFragment)
}
