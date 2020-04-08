package com.example.template.ui.splash.di

import com.example.template.ui.registration_login.login.LoginFragment
import com.example.template.ui.splash.SplashFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [SplashModule::class, SplashBindModule::class])
interface SplashComponent : AndroidInjector<SplashFragment> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<SplashFragment>
}
