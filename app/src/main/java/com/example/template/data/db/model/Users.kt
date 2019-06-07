package com.example.template.data.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor


@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String="",
    @ColumnInfo(name = "surname")
    var surname: String=""){


}



