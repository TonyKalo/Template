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
    fun getAllUsers(): Single<List<Users>>

    @Insert
    fun insertUser(users:Users):Completable

    @Delete
    fun deleteUser(user:Users):Completable

    @Query("DELETE FROM users")
    fun deleteAllUsers():Completable

}