package com.example.template.ui.splash.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.template.core.data.db.entity.Users

@Dao
interface SplashDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<Users>
}
