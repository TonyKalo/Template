package com.example.template.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.template.di.ViewModelFactory
import com.example.template.di.qualifiers.SharedViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelBindModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @Singleton
    @SharedViewModelFactory
    abstract fun bindSharedViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
