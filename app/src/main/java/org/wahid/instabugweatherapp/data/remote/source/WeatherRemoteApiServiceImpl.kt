package org.wahid.instabugweatherapp.data.remote.source

import android.util.Log
import org.json.JSONObject
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.AuthenticationException
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.NotFoundException
import org.wahid.instabugweatherapp.data.custom_exceptions.WeatherRemoteCustomException.ServerErrorException
import org.wahid.instabugweatherapp.data.model.VisualCrossingResponseDto
import org.wahid.instabugweatherapp.data.repository.BuildConfig
import org.wahid.instabugweatherapp.utils.AppExecutors
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.WeatherUnits
import org.wahid.instabugweatherapp.utils.parseTimeline
import java.net.HttpURLConnection
import java.net.URL

class WeatherRemoteApiServiceImpl() : WeatherRemoteApiService {

    override fun fetchWeatherJson(
        lat: Double,
        lon: Double,
        units: WeatherUnits,
        callback: Callback<VisualCrossingResponseDto>,
    ) {
        fun prepareUrl(): String {
//        "${BuildConfig.BASE_URL}$lat&,$lon?unitGroup=$units&key=${BuildConfig.API_KEY}&contentType=json"
            val url = StringBuilder()
            url.append(BuildConfig.BASE_URL)
            url.append(lat)
            url.append(",")
            url.append(lon)
            url.append("?unitGroup=")
            url.append(units)
            url.append("&key=")
            url.append(BuildConfig.API_KEY)
            url.append("&contentType=json")
            return url.toString()
        }

        AppExecutors.networkIO.execute {
            val url = prepareUrl()
            var conn: HttpURLConnection? = null
            try {
                conn = (URL(url).openConnection() as HttpURLConnection).apply {
                    requestMethod = "GET"
                    connectTimeout = 10_000
                    readTimeout = 10_000
                }

                val code = conn.responseCode
                val body = (if (code >= 400) conn.errorStream else conn.inputStream)
                    ?.bufferedReader()?.use { it.readText() } ?: ""

                when(code){
                    in 200..299 -> body
                    401 -> throw AuthenticationException()
                    404 -> throw NotFoundException()
                    in 500..599 -> throw ServerErrorException()
                }

                val rawJson = JSONObject(body)
                val visualCrossingResponseDto = parseTimeline(rawJson)
                callback.onSuccess(visualCrossingResponseDto)
            } catch (
                e: Exception
            ) {
                callback.onError(e)
                Log.d("HttpURLConnection", "fetchWeatherJson: ${e.message}")
            }finally {
                conn?.disconnect()
            }


        }
    }
}