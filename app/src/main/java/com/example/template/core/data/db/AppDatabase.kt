package com.example.template.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.template.core.data.db.dao.UsersDao
import com.example.template.core.data.db.entity.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UsersDao
}
