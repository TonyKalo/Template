package com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di

import com.example.template.ui.splash.SplashFragment
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class, SplashBindModule::class])
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SplashComponent
    }

    fun inject(fragment: SplashFragment)
}
