package com.example.template.di.module

import android.app.Application
import android.content.Context
import android.os.Handler
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
object DataModule {


    @Singleton
    @Provides
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabaseInstance(context)
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideSPHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper {
        return appPreferenceHelper
    }






}