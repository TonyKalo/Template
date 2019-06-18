package com.example.template.di.components

import com.example.template.di.module.ActivityModule
import com.example.template.di.scopes.PerActivity
import com.example.template.ui.login.LoginActivity
import dagger.Component

@PerActivity
@Component (dependencies = arrayOf(AppComponent::class),modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(activity: LoginActivity)
}