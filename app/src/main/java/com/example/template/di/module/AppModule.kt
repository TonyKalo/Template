package com.example.template.di.module

import android.app.Application
import android.content.Context
import com.example.template.di.qualifiers.AppContext
import dagger.Module
import dagger.Provides
import com.example.template.data.AppDataManager
import com.example.template.data.DataManager
import javax.inject.Singleton
import com.example.template.data.db.AppDatabase




@Module
class AppModule(private var application: Application) {

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application
    }


    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getDatabaseInstance(application)
    }

}