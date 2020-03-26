package com.example.template.di.module

import androidx.lifecycle.ViewModel
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel
import com.example.template.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationActivityViewModel::class)
    abstract fun bindViewModel(viewModel: RegistrationActivityViewModel): ViewModel
}
