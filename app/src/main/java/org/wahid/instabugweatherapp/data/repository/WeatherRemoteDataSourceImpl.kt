package org.wahid.instabugweatherapp.data.repository

import android.util.Log
import org.json.JSONObject
import org.wahid.instabugweatherapp.data.model.VisualCrossingResponse
import org.wahid.instabugweatherapp.data.remote.source.WeatherRemoteApiService
import org.wahid.instabugweatherapp.utils.WeatherUnits
import org.wahid.instabugweatherapp.utils.parseTimeline
import java.net.HttpURLConnection
import java.net.URL

class WeatherRemoteDataSourceImpl : WeatherRemoteApiService {

    override fun fetchWeatherJson(lat: Double, lon: Double, units: WeatherUnits): VisualCrossingResponse {
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
        val url = prepareUrl()

        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connectTimeout = 10_000
            conn.readTimeout = 10_000
            val rawJson =  JSONObject(conn.inputStream.bufferedReader().use { it.readText() })
            return parseTimeline(rawJson)

        }catch (
            e:Exception
        ){
            Log.d("fetchWeatherJson", "fetchWeatherJson: ${e.message}")
            throw e
        }

    }


}