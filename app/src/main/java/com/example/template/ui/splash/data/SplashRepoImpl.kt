package com.example.template.ui.splash.data

import com.example.template.core.data.Result
import com.example.template.core.data.Result.Error
import com.example.template.core.data.Result.Success
import com.example.template.core.data.db.AppDatabase
import com.example.template.core.data.sp.AppSharedPreference
import com.example.template.ui.splash.data.network.SplashApiService
import com.example.template.utils.extensions.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepoImpl @Inject constructor(private val db: AppDatabase, private val api: SplashApiService, private val sp: AppSharedPreference) : SplashRepo {

    override suspend fun getData(): Result<String> = withContext(Dispatchers.IO) {
        try {
            delay(3000)
            return@withContext Success("Success")
        } catch (e: Exception) {
            return@withContext Error(e)
        }
    }
}
