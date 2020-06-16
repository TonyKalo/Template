package com.example.template.ui.main_screen.picture_fragment.di

import androidx.lifecycle.ViewModel
import com.example.template.core.di.scopes.ViewModelKey
import com.example.template.ui.main_screen.picture_fragment.PictureViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PictureBindModule {

    @Binds
    @IntoMap
    @ViewModelKey(PictureViewModel::class)
    abstract fun bindViewModel(viewModel: PictureViewModel): ViewModel
}
