package org.wahid.instabugweatherapp.domain.repository

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.WeatherUnits

interface WeatherRepository {
    @Throws(Exception::class)
    fun getCurrentWeather(
        lat: Double,
        lon: Double,
        unit: WeatherUnits = WeatherUnits.metric,
        callback: Callback<DomainWeatherDay>,
    )

    @Throws(Exception::class)
    fun getFiveDayForecast(
        unit: WeatherUnits = WeatherUnits.metric,
        callback: Callback<List<DomainWeatherDay>>,
    )
}
