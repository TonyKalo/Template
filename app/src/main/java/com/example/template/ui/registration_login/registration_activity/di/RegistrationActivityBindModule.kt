package com.example.template.ui.registration_login.registration_activity.di

import androidx.lifecycle.ViewModel
import com.example.template.core.di.scopes.ViewModelKey
import com.example.template.ui.registration_login.registration_activity.RegistrationActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RegistrationActivityBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationActivityViewModel::class)
    abstract fun bindRegistrationActivityViewModel(viewModel: RegistrationActivityViewModel): ViewModel
}
