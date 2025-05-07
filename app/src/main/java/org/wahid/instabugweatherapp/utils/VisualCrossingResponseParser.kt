package org.wahid.instabugweatherapp.utils

import org.json.JSONObject
import org.wahid.instabugweatherapp.data.model.DayWeather
import org.wahid.instabugweatherapp.data.model.VisualCrossingResponseDto

fun parseTimeline(json: JSONObject): VisualCrossingResponseDto {
    val daysList = mutableListOf<DayWeather>()
    val arr = json.getJSONArray("days")
    for (i in 0 until arr.length()) {
        val d = arr.getJSONObject(i)
        daysList += DayWeather(
            date        = d.getString("datetime"),
            tempMax     = d.getDouble("tempmax"),
            tempMin     = d.getDouble("tempmin"),
            temp        = d.getDouble("temp"),
            humidity    = d.getDouble("humidity"),
            precip      = d.getDouble("precip"),
            windSpeed   = d.getDouble("windspeed"),
            pressure    = d.getDouble("pressure"),
            description = d.getString("description"),

        )
    }
    return VisualCrossingResponseDto(
        queryCost       = json.getInt("queryCost"),
        latitude        = json.getDouble("latitude"),
        longitude       = json.getDouble("longitude"),
        resolvedAddress = json.getString("resolvedAddress"),
        address         = json.getString("address"),
        timezone        = json.getString("timezone"),
        tzoffset        = json.getDouble("tzoffset"),
        description     = json.getString("description"),
        days            = daysList
    )
}