package com.example.template.di.module

import com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di.*
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