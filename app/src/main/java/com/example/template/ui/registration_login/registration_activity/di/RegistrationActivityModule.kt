package com.example.template.ui.registration_login.registration_activity.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RegistrationActivityModule {

    @Provides
    fun provideGson(): String {
        return "asd"
    }
}
