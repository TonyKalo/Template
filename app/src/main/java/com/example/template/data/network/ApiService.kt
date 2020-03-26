package com.example.template.data.network

import com.example.template.data.network.pojo.Currency
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("tecajn/v1/")
    fun getCurrency(): Single<ArrayList<Currency>>
}
