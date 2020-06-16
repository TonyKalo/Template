package com.example.template.core.di.module

import android.app.Application
import android.content.Context
import com.example.template.TemplateApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideApplication(app: TemplateApp): Application = app

    @Provides
    @Singleton
    fun provideAppContext(app: Application): Context {
        return app.applicationContext
    }
}
