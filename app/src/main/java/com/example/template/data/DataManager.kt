package com.example.template.data

import com.example.template.data.db.AppDatabase
import com.example.template.data.network.ApiHelper

interface DataManager{

    fun getDb(): AppDatabase

    fun getApi(): ApiHelper
}