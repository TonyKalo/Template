package com.example.template.data.network

import com.example.template.data.network.pojo.Currency
import io.reactivex.Single

interface ApiHelper {

    fun getCurrencies():Single<Currency>
}