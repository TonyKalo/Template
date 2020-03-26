package com.example.template.ui.main_screen.permission_fragment.di

import androidx.lifecycle.ViewModel
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.main_screen.permission_fragment.PermissionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PermissionBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(PermissionViewModel::class)
    abstract fun bindViewModel(viewModel: PermissionViewModel): ViewModel
}
