package com.example.template.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.template.core.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBindModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
