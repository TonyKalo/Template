package com.example.template.di.components

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.template.TemplateApp
import com.example.template.data.DataManager
import com.example.template.di.module.AppModule
import com.example.template.di.module.SharedViewModelModule
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.qualifiers.SharedViewModelFactory
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component (modules = [AppModule::class,SharedViewModelModule::class])
interface AppComponent {

    fun inject(app: TemplateApp)

    fun getDataManager():DataManager

    @AppContext
    fun getAppContext(): Context

    @SharedViewModelFactory
    fun viewModelFactory(): ViewModelProvider.Factory
}
