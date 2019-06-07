package com.example.template.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.template.data.db.model.Users
import io.reactivex.Completable
import io.reactivex.Single



@Dao
interface UsersDao{

    @Query("SELECT * FROM users")
    fun getAllUsers(): Array<Users>

    @Insert
    fun insertUser(users:Users)

    @Delete
    fun deleteUser(user:Users)

    @Query("DELETE FROM users")
    fun deleteAllUsers()


}