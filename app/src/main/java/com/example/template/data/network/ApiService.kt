package com.example.template.data.network

import com.example.template.data.network.pojo.Currency
import io.reactivex.Single
import retrofit2.http.GET


abstract class ApiService {

    @GET("")
    abstract fun getCurrency() : Single<Currency>

}