package com.example.template.data

import com.example.template.data.db.AppDatabase

interface DataManager{

    fun getDb(): AppDatabase
}