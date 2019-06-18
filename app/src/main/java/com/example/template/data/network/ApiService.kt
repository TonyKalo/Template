package com.example.template.data.network

import com.example.template.data.network.pojo.Currency
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {

    @GET("")
    fun getCurrency() : Single<Currency>

}