package com.example.template.core.data.sp

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPreferenceImpl @Inject constructor(private val sp: SharedPreferences) : AppSharedPreference {

    private val KEY_USER_NAME = "PREF_KEY_USER_NAME"

    override var username: String
        get() = sp.getString(KEY_USER_NAME, "")!!
        set(username) { sp.edit().putString(KEY_USER_NAME, username).apply() }
}
