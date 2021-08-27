package com.example.template.core.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.template.core.data.db.AppDatabase
import com.example.template.core.data.sp.AppSharedPreference
import com.example.template.core.data.sp.AppSharedPreferenceImpl
import com.example.template.utils.DATABASE_NAME
import com.example.template.utils.SP_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideAppSP(appPreferenceHelper: AppSharedPreferenceImpl): AppSharedPreference {
        return appPreferenceHelper
    }
}
