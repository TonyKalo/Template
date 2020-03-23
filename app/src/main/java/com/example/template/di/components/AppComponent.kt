package com.example.template.di.components

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.cyberslabs.bandsbuddy.di.module.DataModule
import com.cyberslabs.bandsbuddy.di.module.NetworkModule
import com.cyberslabs.bandsbuddy.ui.main_screen.calendar_fragment.di.*
import com.example.template.data.DataManager
import com.example.template.di.module.SharedViewModelModule
import com.example.template.di.module.SubcomponentsModule
import com.example.template.di.module.ViewModelBindModule
import com.example.template.di.qualifiers.SharedViewModelFactory
import com.example.template.utils.scheduler.SchedulerProvider
import dagger.BindsInstance
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CompletableJob
import javax.inject.Singleton


@Singleton
@Component (
    modules = [
        NetworkModule::class,
        DataModule::class,
        SubcomponentsModule::class,
        SharedViewModelModule::class,
        ViewModelBindModule::class,
        BaseFragmentModule::class
    ])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun pictureComponent(): PictureComponent.Factory
    fun permissionComponent(): PermissionComponent.Factory
    fun splashComponent(): SplashComponent.Factory
    fun loginComponent(): LoginComponent.Factory

}
