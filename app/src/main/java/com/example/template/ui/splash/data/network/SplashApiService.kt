package com.example.template.ui.splash.data.network

import com.example.template.ui.splash.data.network.pojo.Currency
import retrofit2.http.GET

interface SplashApiService {

    @GET("tecajn/v1/")
    suspend fun getCurrency(): ArrayList<Currency>
}
