package com.example.template.di.module

import android.app.Activity
import android.content.Context
import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.progress_dialog.CustomProgressDialog
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
    fun provideCustomAlertDialog(): CustomAlertDialog {
        return CustomAlertDialog()
    }

    @Provides
    fun provideCustomProgressDialog(): CustomProgressDialog {
        return CustomProgressDialog()
    }

}