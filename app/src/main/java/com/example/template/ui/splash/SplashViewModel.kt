package com.example.template.ui.splash


import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.ui.base.BaseViewModel
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(@AppContext appContext: Context, dataManager: DataManager, schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable)
    : BaseViewModel(appContext,dataManager,schedulerProvider,compositeDisposable) {

    var navigateToNextScreen = MutableLiveData<Boolean>()
    var a = 0


     fun loadData(){


             var work = GlobalScope.launch(Dispatchers.Default) {

                 val task1 = async { b() }
                 val task2 = async { c() }
                 val task3 = async {
                     try { a()
                     }catch (e:Exception){
                         Log.e("TAG",e.localizedMessage)
                        }
                 }

                 awaitAll(task1, task2, task3)


             }


            if(work.isCompleted)Log.e("TAG","complete")else Log.e("TAG","fail")


    }

    suspend fun a():Int{
        delay(3000)
        var b =0/0
        return 2;
    }
    suspend fun b():Int{
        delay(3000)

        return 2;
    }
    suspend fun c():Int{
        delay(3000)

        return 2;
    }

}