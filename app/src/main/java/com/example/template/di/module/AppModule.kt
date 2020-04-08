package com.example.template.di.module

import android.app.Application
import android.content.Context
import com.example.template.TemplateApp
import com.example.template.data.AppDataManager
import com.example.template.data.DataManager
import com.example.template.data.db.AppDatabase
import com.example.template.data.network.ApiHelper
import com.example.template.data.network.AppApiHelper
import com.example.template.data.sp.AppPreferenceHelper
import com.example.template.data.sp.PreferenceHelper
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
