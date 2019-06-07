package com.example.template.di.components

import com.example.template.TemplateApp
import com.example.template.data.DataManager
import com.example.template.di.module.AppModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component (modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: TemplateApp)

    fun getDataManager():DataManager
}