package com.example.template.core.data.sp

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPreferenceImpl @Inject constructor(private val sp: SharedPreferences) : AppSharedPreference {

    private val PREF_FILE_NAME = "PREF_FILE_NAME"
    private val KEY_USER_NAME = "PREF_KEY_USER_NAME"

    override fun setUsername(username: String) {
        sp.edit().putString(KEY_USER_NAME, username).apply()
    }

    override fun getUsername(): String {
        return sp.getString(KEY_USER_NAME, "").toString()
    }
}
