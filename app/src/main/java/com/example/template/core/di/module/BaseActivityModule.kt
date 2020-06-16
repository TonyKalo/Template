package com.example.template.core.di.module

import com.cyberslabs.customwidgets.alert_dialog.CustomAlertDialog
import com.cyberslabs.customwidgets.progress_dialog.CustomProgressDialog
import com.example.template.core.di.qualifiers.BaseActivityScope
import dagger.Module
import dagger.Provides

@Module
object BaseActivityModule {

    @BaseActivityScope
    @Provides
    fun provideCustomAlertDialog(): CustomAlertDialog {
        return CustomAlertDialog()
    }

    @BaseActivityScope
    @Provides
    fun provideCustomProgressDialog(): CustomProgressDialog {
        return CustomProgressDialog()
    }
}
