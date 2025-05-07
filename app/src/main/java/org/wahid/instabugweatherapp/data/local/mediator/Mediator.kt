package org.wahid.instabugweatherapp.data.local.mediator

import org.wahid.instabugweatherapp.data.local.db.SharedPrefs
import org.wahid.instabugweatherapp.data.local.db.WeatherSqliteDb
import org.wahid.instabugweatherapp.data.local.db.model.WeatherDbEntity
import org.wahid.instabugweatherapp.data.model.VisualCrossingResponseDto
import org.wahid.instabugweatherapp.data.remote.source.WeatherRemoteApiServiceImpl
import org.wahid.instabugweatherapp.utils.AppExecutors
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.Constant.INIT_LAUNCH_KEY
import org.wahid.instabugweatherapp.utils.Constant.CACHE_TIME_OUT_KEY
import kotlin.time.Duration.Companion.hours

class Mediator(
    val apiServiceImpl: WeatherRemoteApiServiceImpl,
    db: WeatherSqliteDb,
    val prefs: SharedPrefs,
) {
    val dbDao = db.getDao()
    val isFirstLaunch: Boolean = prefs.getAll()[INIT_LAUNCH_KEY] as Boolean
    val cacheTimeOut: Long = prefs.getAll()[CACHE_TIME_OUT_KEY] as Long

    fun load(
        lat: Double,
        lon: Double,
        callback: Callback<WeatherDbEntity>
    ) {

        val currentTime = System.currentTimeMillis()
        var cachedData: List<WeatherDbEntity>? = null
        AppExecutors.diskIO.execute {
            if (isFirstLaunch || cacheTimeOut.plus(1).hours <= currentTime.hours) {
                apiServiceImpl.fetchWeatherJson(lat = lat, lon = lon, callback = object :
                    Callback<VisualCrossingResponseDto> {
                    override fun onSuccess(result: VisualCrossingResponseDto) {
                        val res = result.days?.map {
                            it.toDb()
                        }
                        res?.let {
                            dbDao.replace(days = res)
                            cachedData = res
                            prefs.save(false, cacheTimeOut = currentTime)
                        }
                    }

                    override fun onError(error: Throwable) {
                        callback.onError(error = error)
                    }
                })

            }
            else {

                try {
                    cachedData = dbDao.load()

                } catch (
                    e: Exception,
                ) {
                    callback.onError(error = e)
                }

            }
        }
    }
}