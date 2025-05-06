package org.wahid.instabugweatherapp.data.remote.source

import org.wahid.instabugweatherapp.data.model.VisualCrossingResponse
import org.wahid.instabugweatherapp.utils.WeatherUnits

interface WeatherRemoteApiService {
    @Throws(Exception::class)
    fun fetchWeatherJson(lat: Double, lon: Double,units: WeatherUnits): VisualCrossingResponse
}