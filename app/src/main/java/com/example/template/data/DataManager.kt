package com.example.template.data

import com.example.template.data.db.AppDatabase
import com.example.template.data.db.Database
import com.example.template.data.db.dao.UsersDao

interface DataManager{

    fun getDb(): AppDatabase
}