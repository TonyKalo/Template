package com.example.template.di.components

import android.content.Context
import com.example.template.TemplateApp
import com.example.template.data.DataManager
import com.example.template.di.module.AppModule
import com.example.template.di.qualifiers.AppContext
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component (modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: TemplateApp)

    fun getDataManager():DataManager

    @AppContext
    fun getAppContext(): Context
}
