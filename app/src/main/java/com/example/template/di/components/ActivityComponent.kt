package com.example.template.di.components

import com.example.template.di.module.ActivityModule
import com.example.template.di.module.ViewModelModule
import com.example.template.di.scopes.PerActivity
import com.example.template.ui.activity_main.MainActivity
import com.example.template.ui.login.LoginFragment
import com.example.template.ui.main_screen.permission_fragment.PermissionFragment
import com.example.template.ui.splash.SplashFragment
import com.example.template.ui.splash.SplashViewModel
import dagger.Component

@PerActivity
@Component (dependencies = [AppComponent::class],modules = [ActivityModule::class,ViewModelModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: SplashFragment)
    fun inject(fragment: PermissionFragment)

}