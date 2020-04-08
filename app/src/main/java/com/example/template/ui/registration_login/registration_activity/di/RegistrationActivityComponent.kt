package com.example.template.ui.registration_login.registration_activity.di

import com.example.template.ui.main_screen.main_activity.di.MainActivityBindModule
import com.example.template.ui.registration_login.registration_activity.RegistrationActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [RegistrationActivityModule::class, RegistrationActivityBindModule::class])

interface RegistrationActivityComponent : AndroidInjector<RegistrationActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<RegistrationActivity>

}

