package org.wahid.instabugweatherapp.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object AppExecutors {

    val diskIO: Executor = Executors.newFixedThreadPool(2)

    val mainThread: Handler = Handler(Looper.getMainLooper())
//    val parserThread: ExecutorService = Executors.newSingleThreadExecutor()
}