package com.example.template.ui.registration_login.login.di

import com.example.template.ui.registration_login.login.LoginFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [LoginModule::class, LoginBindModule::class])
interface LoginComponent : AndroidInjector<LoginFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<LoginFragment>
}
