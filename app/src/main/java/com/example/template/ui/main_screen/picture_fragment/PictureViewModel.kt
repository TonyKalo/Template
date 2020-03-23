package com.example.template.ui.main_screen.picture_fragment

import android.content.Context
import com.example.template.data.DataManager
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CompletableJob
import javax.inject.Inject

class PictureViewModel  @Inject constructor(dataManager: DataManager)
    : BaseViewModel(dataManager) {

}