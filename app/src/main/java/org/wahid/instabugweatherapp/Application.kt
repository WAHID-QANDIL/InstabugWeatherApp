package org.wahid.instabugweatherapp

import android.app.Application
import android.content.Context

open class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    open lateinit var context: Context



}