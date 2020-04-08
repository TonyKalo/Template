package com.example.template.ui.main_screen.main_activity.di

import androidx.lifecycle.ViewModel
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.main_screen.main_activity.MainActivityViewModel
import com.example.template.ui.registration_login.login.LoginViewModel
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}
