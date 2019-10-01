package com.example.template.di.module

import android.app.Application
import android.content.Context
import com.example.template.di.qualifiers.AppContext
import dagger.Module
import dagger.Provides
import com.example.template.data.AppDataManager
import com.example.template.data.DataManager
import javax.inject.Singleton
import com.example.template.data.db.AppDatabase
import com.example.template.data.network.ApiHelper
import com.example.template.data.network.AppApiHelper
import com.example.template.data.network.ApiService
import com.example.template.data.sp.AppPreferenceHelper
import com.example.template.data.sp.PreferenceHelper
import com.example.template.utils.BASE_URL
import com.example.template.utils.CONNECT_TIMEOUT_SECONDS
import com.example.template.utils.READ_TIMEOUT_SECONDS
import com.example.template.utils.WRITE_TIMEOUT_SECONDS
import com.example.template.utils.scheduler.AppSchedulerProvider
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.SupervisorJob
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit







@Module
class AppModule(private var application: Application) {

    @AppContext
    @Provides
    fun provideAppContext(): Context {
        return application
    }


    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Singleton
    @Provides
    fun provideCompletableJob(): CompletableJob {
        return SupervisorJob()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getDatabaseInstance(application)
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper):ApiHelper{
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideSPHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper {
        return appPreferenceHelper
    }

    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(baseURL: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideApiService(): ApiService {
        return provideRetrofit(BASE_URL, provideClient()).create(ApiService::class.java)
    }

}