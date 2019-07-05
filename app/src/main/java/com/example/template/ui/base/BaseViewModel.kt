package com.example.template.ui.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.R
import com.example.template.data.DataManager
import com.example.template.di.qualifiers.AppContext
import com.example.template.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import com.example.template.utils.isNetworkConnected
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import javax.inject.Inject

import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_FORBIDDEN
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.net.ssl.HttpsURLConnection


open class BaseViewModel @Inject constructor(@AppContext val appContext:Context,val dataManager:DataManager, val schedulerProvider: SchedulerProvider, val compositeDisposable: CompositeDisposable) : ViewModel() {


   val isLoading = MutableLiveData<Boolean>()
   val handleErrorString= MutableLiveData<String>()

    fun handleError(e: Throwable){
        Log.e("TAG",e.localizedMessage)
        if(!isNetworkConnected(appContext)){
            handleErrorString.value=appContext.getString(R.string.err_no_net)
        }else if(e is HttpException){
           when (e.code()) {
              HttpsURLConnection.HTTP_UNAUTHORIZED -> handleErrorString.value=appContext.getString(R.string.err_unauthorised)
              HttpsURLConnection.HTTP_FORBIDDEN -> handleErrorString.value=appContext.getString(R.string.err_forbidden)
              HttpsURLConnection.HTTP_INTERNAL_ERROR -> handleErrorString.value=appContext.getString(R.string.err_internal_server)
              HttpsURLConnection.HTTP_BAD_REQUEST -> handleErrorString.value=appContext.getString(R.string.err_bad_request)
              else -> handleErrorString.value=e.localizedMessage
           }
        }else if(e is JsonSyntaxException){
           handleErrorString.value=appContext.getString(R.string.err_not_responding)
        }else{
           handleErrorString.value=e.localizedMessage
        }

    }


}