package com.example.template.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.template.core.data.db.entity.Users
import com.example.template.ui.splash.data.db.dao.SplashDao

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getSplashDao(): SplashDao
}
