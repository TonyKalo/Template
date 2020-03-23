package com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di

import com.example.template.ui.main_screen.permission_fragment.PermissionFragment
import com.example.template.ui.main_screen.picture_fragment.PictureFragment
import com.example.template.ui.registration_login.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [PermissionModule::class, PermissionBindModule::class])
interface PermissionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): PermissionComponent
    }

    fun inject(fragment: PermissionFragment)
}
