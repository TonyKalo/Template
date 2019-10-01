package com.example.template.di.components

import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.ViewModelProvider
import com.example.template.TemplateApp
import com.example.template.data.DataManager
import com.example.template.di.module.AppModule
import com.example.template.di.module.SharedViewModelModule
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.utils.scheduler.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CompletableJob
import javax.inject.Singleton


@Singleton
@Component (modules = [AppModule::class,SharedViewModelModule::class])
interface AppComponent {

    fun inject(app: TemplateApp)

    fun getDataManager():DataManager
    fun getSchedulerProvider(): SchedulerProvider
    fun getCompositeDisposable(): CompositeDisposable
    fun getCompletableJob(): CompletableJob

    @AppContext
    fun getAppContext(): Context

    @SharedViewModelFactory
    fun viewModelFactory(): ViewModelProvider.Factory
}
