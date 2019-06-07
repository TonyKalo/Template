package com.example.template

import android.app.Application
import com.example.template.di.components.AppComponent
import com.example.template.di.components.DaggerAppComponent

import com.example.template.di.module.AppModule


class TemplateApp : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }


    fun getAppComponent():AppComponent{
        return appComponent
    }


}