package com.example.template.ui.splash.data

import com.example.template.core.data.Result
import com.example.template.core.data.Result.Error
import com.example.template.core.data.Result.Success
import com.example.template.core.data.sp.AppSharedPreference
import com.example.template.ui.splash.data.db.dao.SplashDao
import com.example.template.ui.splash.data.network.SplashApiService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SplashRepoImpl @Inject constructor(private val db: SplashDao, private val api: SplashApiService, private val sp: AppSharedPreference) : SplashRepo {

    override suspend fun getData(): Result<String> = withContext(Dispatchers.IO) {

        try {
//            val userList = db.getAllUsers()
//            val currency = api.getCurrency()
//            val data = sp.getUsername()
            delay(3000)
            return@withContext Success("Success")
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }
}
