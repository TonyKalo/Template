package com.example.template.ui.splash.data

import com.example.template.core.data.Result
import kotlinx.coroutines.flow.Flow

interface SplashRepo {

   fun getData(): Flow<Result<String>>
}
