package com.example.template.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.template.di.qualifiers.ActivityContext
import com.example.template.di.qualifiers.AppContext
import com.example.template.di.scopes.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private var activity:Activity) {

@ActivityContext
@Provides
fun provideAppContext():Context{
    return activity
}

}