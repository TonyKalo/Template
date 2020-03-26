package com.example.template.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.template.data.db.dao.UsersDao
import com.example.template.data.db.model.Users

@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val DATABASE_NAME = "database"
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabaseInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase::class.java,
                        DATABASE_NAME
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as AppDatabase
        }
    }

    fun destroyInstance() {
        instance = null
    }

    public abstract fun getUsersDao(): UsersDao
}
