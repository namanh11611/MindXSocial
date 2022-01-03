package com.namanh.mindx.utils

import android.content.Context
import androidx.preference.PreferenceManager

object AppUtils {
    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "3000f36cf9994c78b43534ea1ba66c6e"
    const val DATABASE_NAME = "kotlin_base"
    const val DATABASE_VERSION = 1

    const val PREF_USER_NAME = "user_name"
    const val PREF_PASSWORD = "password"

    const val BUNDLE_AUTHOR = "author"
    const val BUNDLE_CONTENT = "content"

    fun putPrefString(context: Context?, key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(key, value).apply()
    }

    fun getPrefString(context: Context?, key: String): String {
        return if (context == null) "" else PreferenceManager.getDefaultSharedPreferences(context)
            .getString(key, "") ?: ""
    }
}