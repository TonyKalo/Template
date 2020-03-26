package com.example.template.data.network

import com.example.template.data.network.pojo.Currency
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppApiHelper @Inject constructor(private val service: ApiService) : ApiHelper {

    override fun getCurrencies(): Single<ArrayList<Currency>> {
        return service.getCurrency()
    }
}
