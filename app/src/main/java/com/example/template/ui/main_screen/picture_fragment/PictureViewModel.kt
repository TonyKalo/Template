package com.example.template.ui.main_screen.picture_fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.ui.base.callbacks.PermissionCallback
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CompletableJob
import javax.inject.Inject

class PictureViewModel  @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable, coroutineJob: CompletableJob)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable, coroutineJob) {

}