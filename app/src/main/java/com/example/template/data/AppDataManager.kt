package com.example.template.data

import android.content.Context
import com.example.template.data.db.AppDatabase
import com.example.template.data.network.ApiHelper
import com.example.template.data.sp.PreferenceHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(private val context: Context, private val db: AppDatabase, private val api: ApiHelper, private val sp: PreferenceHelper) : DataManager {

    override fun getSP(): PreferenceHelper {
        return sp
    }

    override fun getApi(): ApiHelper {
        return api
    }

    override fun getDb(): AppDatabase {
        return db
    }
}
