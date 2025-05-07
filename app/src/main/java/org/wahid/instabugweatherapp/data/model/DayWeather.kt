package org.wahid.instabugweatherapp.data.model

import org.wahid.instabugweatherapp.data.local.db.model.WeatherDbEntity


data class DayWeather(
    val date:           String,
    val tempMax:        Double,
    val tempMin:        Double,
    val temp:           Double,
    val humidity:       Double,
    val precip:         Double,
    val windSpeed:      Double,
    val pressure:       Double,
    val description:    String,
//    val address:        String, all days belongs to the same location/address
){
    fun toDb(): WeatherDbEntity{
        return WeatherDbEntity(
            date        = date,
            tempMax     = tempMax,
            tempMin     = tempMin,
            temp        = temp,
            humidity    = humidity,
            precip      = precip,
            windSpeed   = windSpeed,
            pressure    = pressure,
            description = description,
            address     = "UNKNOWN",
            lastUpdate  = System.currentTimeMillis()
        )
    }
}
