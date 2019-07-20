package com.example.template.data.sp

import android.content.Context
import android.content.SharedPreferences
import com.example.template.di.qualifiers.AppContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferenceHelper: PreferenceHelper {


    private val PREF_FILE_NAME = "PREF_FILE_NAME"

    private val KEY_USER_NAME = "PREF_KEY_USER_NAME"

    var sp:SharedPreferences? = null

    @Inject
    constructor(@AppContext appContext: Context){
        sp=appContext.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE)
    }

    override fun setUsername(username: String) {
       sp?.edit()?.putString(KEY_USER_NAME,username)?.apply()
    }

    override fun getUsername(): String {
        return sp?.getString(KEY_USER_NAME,"").toString()
    }

}