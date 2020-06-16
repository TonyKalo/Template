package com.example.template.ui.main_screen.main_activity.di

import com.example.template.ui.main_screen.main_activity.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MainActivityModule::class, MainActivityBindModule::class])

interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Factory
    abstract class Factory : AndroidInjector.Factory<MainActivity>
}
