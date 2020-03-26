package com.example.template.di.module

import com.example.template.ui.main_screen.permission_fragment.di.PermissionComponent
import com.example.template.ui.main_screen.picture_fragment.di.PictureComponent
import com.example.template.ui.registration_login.login.di.LoginComponent
import com.example.template.ui.splash.di.SplashComponent
import dagger.Module

@Module(
    subcomponents = [
        SplashComponent::class,
        LoginComponent::class,
        PictureComponent::class,
        PermissionComponent::class
    ]
)
object SubcomponentsModule