package org.wahid.instabugweatherapp.domain.repository

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.WeatherUnits

interface WeatherRepository {
    @Throws(Exception::class)
    fun getCurrentWeather(lat: Double, lon: Double, unit: WeatherUnits =  WeatherUnits.metric): DomainWeatherDay

    @Throws(Exception::class)
    fun getFiveDayForecast(lat: Double, lon: Double, unit: WeatherUnits = WeatherUnits.metric): List<DomainWeatherDay>

}