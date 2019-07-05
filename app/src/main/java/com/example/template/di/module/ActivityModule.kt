package com.example.template.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.template.di.qualifiers.ActivityContext
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.scopes.PerActivity
import com.example.template.utils.AppSchedulerProvider
import com.example.template.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private var activity:Activity) {

@ActivityContext
@Provides
fun provideActivityContext():Context{
    return activity
}

@Provides
fun provideSchedulerProvider():SchedulerProvider{
    return AppSchedulerProvider()
}

    @Provides
    fun provideCompositeDisposable (): CompositeDisposable{
        return CompositeDisposable()
    }

}