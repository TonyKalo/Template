package com.example.template.core.di.components

import com.example.template.TemplateApp
import com.example.template.core.di.module.AppModule
import com.example.template.core.di.module.BaseActivityModule
import com.example.template.core.di.module.DataModule
import com.example.template.core.di.module.NetworkModule
import com.example.template.core.di.module.SharedViewModelModule
import com.example.template.core.di.module.SubcomponentsModule
import com.example.template.core.di.module.ViewModelBindModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DataModule::class,
        SubcomponentsModule::class,
        SharedViewModelModule::class,
        ViewModelBindModule::class,
        BaseActivityModule::class
    ])
interface AppComponent : AndroidInjector<TemplateApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: TemplateApp): AndroidInjector<TemplateApp>
    }
}
