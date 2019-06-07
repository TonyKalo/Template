package com.example.template.data

import android.content.Context
import com.example.template.data.db.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val context: Context,private val db: AppDatabase) :DataManager {


    override fun getDb(): AppDatabase {
        return db
    }
}