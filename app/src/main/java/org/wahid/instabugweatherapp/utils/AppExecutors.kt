package org.wahid.instabugweatherapp.utils

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutors {

    val diskIO: Executor = Executors.newFixedThreadPool(2)
    val networkIO: Executor = Executors.newSingleThreadExecutor()

    val mainThread: Handler = Handler(Looper.getMainLooper())
    private val locationThread: HandlerThread = HandlerThread("locationThread").apply { start() }
    val locationLooper: Looper = locationThread.looper
}