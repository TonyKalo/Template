package com.example.template.ui.splash.data

import com.example.template.core.data.Result

interface SplashRepo {

    suspend fun getData(): Result<String>
}
