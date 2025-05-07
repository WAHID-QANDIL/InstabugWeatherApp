package org.wahid.instabugweatherapp.data.local.db

import android.annotation.SuppressLint
import android.content.Context
import org.wahid.instabugweatherapp.utils.Constant.SHARED_PREFS_NAME
import org.wahid.instabugweatherapp.utils.Constant.CACHE_TIME_OUT_KEY
import org.wahid.instabugweatherapp.utils.Constant.INIT_LAUNCH_KEY
import androidx.core.content.edit

class SharedPrefs(context: Context) {
    private val prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)


    fun getBool(key: String) = prefs.getBoolean(key, false)
    fun getLong(key: String) = prefs.getLong(key, 0L)

    @SuppressLint("CommitPrefEdits")
    fun putBoolean(initLaunch: Boolean) {

        prefs.edit() {
            putBoolean(INIT_LAUNCH_KEY, initLaunch)
        }


    }
    fun putLong(cacheTimeOut: Long) {

        prefs.edit() {
            putLong(CACHE_TIME_OUT_KEY, cacheTimeOut)
        }


    }
}