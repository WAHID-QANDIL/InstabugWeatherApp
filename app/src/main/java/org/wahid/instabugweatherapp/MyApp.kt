package org.wahid.instabugweatherapp

import android.app.Application
import android.content.Context

open class MyApp : Application() {

    companion object {

        private var instance: MyApp? = null

        // safe, non-null accessor
        fun getAppContext(): Context = instance!!.applicationContext
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }



}