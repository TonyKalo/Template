package com.example.template.di.components

import android.content.Context
import com.cyberslabs.bandsbuddy.di.module.DataModule
import com.cyberslabs.bandsbuddy.di.module.NetworkModule
import com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di.*
import com.example.template.TemplateApp
import com.example.template.di.module.SharedViewModelModule
import com.example.template.di.module.SubcomponentsModule
import com.example.template.di.module.ViewModelBindModule
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Singleton
@Component (
    modules = [
        NetworkModule::class,
        DataModule::class,
        SubcomponentsModule::class,
        SharedViewModelModule::class,
        ViewModelBindModule::class,
        BaseActivityModule::class,
        ActivityBindModule::class
    ])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun pictureComponent(): PictureComponent.Factory
    fun permissionComponent(): PermissionComponent.Factory
    fun splashComponent(): SplashComponent.Factory
    fun loginComponent(): LoginComponent.Factory

    fun inject(activity:RegistrationActivity)
    fun inject(app:TemplateApp)


}
