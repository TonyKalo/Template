package com.example.template


import android.app.Application
import android.util.Log
import androidx.room.Index
import com.example.template.data.DataManager
import com.example.template.di.components.AppComponent
import com.example.template.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import java.util.logging.Handler
import javax.inject.Inject


class TemplateApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent= DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
    }


    fun getAppComponent():AppComponent{
        return appComponent
    }




}