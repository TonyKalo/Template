package com.example.template.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.template.di.DaggerViewModelFactory
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.main_screen.MainScreenSharedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class SharedViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenSharedViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainScreenSharedViewModel): ViewModel


    @Binds
    @Singleton
    @SharedViewModelFactory
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

}