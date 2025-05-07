package org.wahid.instabugweatherapp.data.local.db

import android.annotation.SuppressLint
import android.content.Context
import org.wahid.instabugweatherapp.utils.Constant.SHARED_PREFS_NAME
import org.wahid.instabugweatherapp.utils.Constant.CACHE_TIME_OUT_KEY
import org.wahid.instabugweatherapp.utils.Constant.INIT_LAUNCH_KEY
import androidx.core.content.edit

class SharedPrefs(context: Context) {
    private val prefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun getAll(): Map<String, *> = prefs.all
    @SuppressLint("CommitPrefEdits")
    fun save(initLaunch: Boolean, cacheTimeOut: Long) {

        prefs.edit() {
            putLong(CACHE_TIME_OUT_KEY, cacheTimeOut)
            putBoolean(INIT_LAUNCH_KEY, initLaunch)
        }


    }
}