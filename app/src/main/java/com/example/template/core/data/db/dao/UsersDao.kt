package com.example.template.core.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.template.core.data.db.entity.Users

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<Users>
}
