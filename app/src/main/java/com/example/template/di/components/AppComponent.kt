package com.example.template.di.components

import com.example.template.TemplateApp
import com.example.template.di.module.AppModule
import com.example.template.di.module.BaseActivityModule
import com.example.template.di.module.DataModule
import com.example.template.di.module.NetworkModule
import com.example.template.di.module.SharedViewModelModule
import com.example.template.di.module.SubcomponentsModule
import com.example.template.di.module.ViewModelBindModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import retrofit2.Retrofit
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
