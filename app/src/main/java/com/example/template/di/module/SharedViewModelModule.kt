package com.example.template.di.module

import androidx.lifecycle.ViewModel
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SharedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainScreenSharedViewModel::class)
    abstract fun bindRegistrationSharedViewModel(registrationSharedViewModel: MainScreenSharedViewModel): ViewModel
}
