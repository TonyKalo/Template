package com.example.template.ui.splash.di

import com.example.template.ui.splash.data.SplashRepo
import com.example.template.ui.splash.data.SplashRepoImpl
import com.example.template.ui.splash.data.network.SplashApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object SplashModule {
    @Provides
    fun provideSplashRepo(splashRepoImpl: SplashRepoImpl): SplashRepo {
        return splashRepoImpl
    }

    @Provides
    @JvmSuppressWildcards
    fun provideSplashApiService(retrofit: Retrofit): SplashApiService {
        return retrofit.create(SplashApiService::class.java)
    }
}
