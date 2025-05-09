package org.wahid.instabugweatherapp.data.local.db.model

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay


data class WeatherDbEntity (
    val date:           String,
    val temp:           Double,
    val tempMax:        Double,
    val tempMin:        Double,
    val humidity:       Double,
    val precip:         Double,
    val windSpeed:      Double,
    val pressure:       Double,
    val description:    String,
    val lastUpdate:     Long,
    val timeZone:       String
){
    fun toDomain(): DomainWeatherDay{
        return DomainWeatherDay(
            date = date,
            temp = temp,
            maxTemp = tempMax,
            minTemp = tempMin,
            description = description,
            timezone = timeZone,
            pressure = pressure,
            windSpeed = windSpeed,
            precip = precip,
            humidity = humidity,
            lastUpdate = lastUpdate,
            address = timeZone.substringAfter("/")
        )
    }
}