package com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di

import com.example.template.ui.registration_login.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class, LoginBindModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(fragment: LoginFragment)
}
