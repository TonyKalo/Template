package com.example.template.di.components

import android.content.Context
import com.example.template.TemplateApp
import com.example.template.di.module.*
import com.example.template.ui.main_screen.permission_fragment.di.PermissionComponent
import com.example.template.ui.main_screen.picture_fragment.di.PictureComponent
import com.example.template.ui.registration_login.login.di.LoginComponent
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import com.example.template.ui.splash.di.SplashComponent
import dagger.BindsInstance
import dagger.Component
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
