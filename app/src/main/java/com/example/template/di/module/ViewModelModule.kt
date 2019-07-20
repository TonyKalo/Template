package com.example.template.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.template.di.DaggerViewModelFactory
import com.example.template.di.scopes.ViewModelKey
import com.example.template.ui.activity_main.MainViewModel
import com.example.template.ui.login.LoginViewModel
import com.example.template.ui.main_screen.permission_fragment.PermissionViewModel
import com.example.template.ui.main_screen.picture_fragment.PictureViewModel
import com.example.template.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PermissionViewModel::class)
    abstract fun bindPermissionViewModel(permissionViewModel: PermissionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PictureViewModel::class)
    abstract fun bindPictureViewModel(pictureViewModel: PictureViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory





}