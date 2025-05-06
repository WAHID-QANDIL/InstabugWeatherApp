package org.wahid.instabugweatherapp.data.model

data class DayWeather(
    val date: String,
    val tempMax: Double,
    val tempMin: Double,
    val temp: Double,
    val humidity: Double,
    val precip: Double,
    val windSpeed: Double,
    val pressure: Double,
    val description: String,
    val icon: String?
)
