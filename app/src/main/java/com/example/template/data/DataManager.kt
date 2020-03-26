package com.example.template.data

import com.example.template.data.db.AppDatabase
import com.example.template.data.network.ApiHelper
import com.example.template.data.sp.PreferenceHelper

interface DataManager {

    fun getDb(): AppDatabase

    fun getApi(): ApiHelper

    fun getSP(): PreferenceHelper
}
