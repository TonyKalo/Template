package com.example.template

import android.app.Application
import com.example.template.di.components.AppComponent
import com.example.template.di.components.DaggerAppComponent

class TemplateApp : Application() {


    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }


    fun getAppComponent():AppComponent{
        return appComponent
    }


}