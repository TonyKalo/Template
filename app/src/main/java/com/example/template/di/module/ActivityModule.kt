package com.example.template.di.module

import android.app.Activity
import android.content.Context
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.example.template.di.qualifiers.ActivityContext
import com.example.template.utils.scheduler.AppSchedulerProvider
import com.example.template.utils.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private var activity: Activity) {

    @ActivityContext
    @Provides
    fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideCustomAlertDialog(): CustomAlertDialog {
        return CustomAlertDialog()
    }

}