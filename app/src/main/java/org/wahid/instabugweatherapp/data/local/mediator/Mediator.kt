package org.wahid.instabugweatherapp.data.local.mediator

import android.util.Log
import org.wahid.instabugweatherapp.data.local.db.SharedPrefs
import org.wahid.instabugweatherapp.data.local.db.WeatherSqliteDb
import org.wahid.instabugweatherapp.data.local.db.dao.WeatherDao
import org.wahid.instabugweatherapp.data.local.db.model.WeatherDbEntity
import org.wahid.instabugweatherapp.data.model.VisualCrossingResponseDto
import org.wahid.instabugweatherapp.data.remote.source.WeatherRemoteApiServiceImpl
import org.wahid.instabugweatherapp.utils.AppExecutors
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.Constant.INIT_LAUNCH_KEY
import org.wahid.instabugweatherapp.utils.Constant.CACHE_TIME_OUT_KEY
import java.util.concurrent.TimeUnit

class Mediator(
    private val apiServiceImpl: WeatherRemoteApiServiceImpl,
    db: WeatherSqliteDb,
    private val prefs: SharedPrefs,
    private val dbDao: WeatherDao = db.getDao(),

    ) {
    private fun isFirstLaunch() = prefs.getBool(INIT_LAUNCH_KEY)
    private fun lastFetchAt() = prefs.getLong(CACHE_TIME_OUT_KEY)
    val now = System.currentTimeMillis()
    val elapsed = now - lastFetchAt()
    val cacheDuration = TimeUnit.HOURS.toMillis(1)
    val shouldFetch = isFirstLaunch() || (elapsed >= cacheDuration)

    fun loadCurrentWeather(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDbEntity>,
    ) {
        if (shouldFetch) {
            Log.d("shouldFetch", "loadCurrentWeather: $shouldFetch")
            AppExecutors.networkIO.execute {
                Log.d("Fetch", "loadFetchAll: ")
                apiServiceImpl.fetchWeatherJson(lat = lat, lon = lon, callback = object :
                    Callback<VisualCrossingResponseDto> {
                    override fun onSuccess(result: VisualCrossingResponseDto) {
                        val res = result.days.orEmpty().map {
                            it.toDb()
                        }
                        AppExecutors.diskIO.execute {
                            dbDao.replace(days = res)
                            prefs.putLong(cacheTimeOut = now)
                            prefs.putBoolean(false)
                            AppExecutors.mainThread.post {
                                if (res.isNotEmpty()) callback.onSuccess(res.first())
                                else callback.onError(IllegalStateException("No data"))
                            }
                        }
                    }

                    override fun onError(error: Throwable) {
                        AppExecutors.mainThread.post { callback.onError(error = error) }
                    }
                })

            }
        } else {

            AppExecutors.diskIO.execute {
                try {
                    val cachedData = dbDao.getFirst()
                    Log.d("cachedData", "loadCurrentWeather: $cachedData")
                    AppExecutors.mainThread.post { callback.onSuccess(cachedData) }


                } catch (e: Exception) {
                    AppExecutors.mainThread.post { callback.onError(e) }
                }
            }
        }
    }

    fun loadFetchAll(
        callback: Callback<List<WeatherDbEntity>>,
    ) {


        AppExecutors.networkIO.execute {
            Log.d("networkIO", "loadFetchAll: ${Thread.currentThread()}")

            AppExecutors.diskIO.execute {
                Log.d("Load", "loadFetchAll: ${Thread.currentThread()}")
                try {
                    val cachedData = dbDao.loadFiveForcast()
                    AppExecutors.mainThread.post { callback.onSuccess(cachedData) }

                } catch (
                    e: Exception,
                ) {
                    AppExecutors.mainThread.post { callback.onError(error = e) }
                }
            }


        }
    }
}