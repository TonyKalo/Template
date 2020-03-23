package com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di

import androidx.lifecycle.ViewModel
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindViewModel(viewModel: SplashViewModel): ViewModel
}