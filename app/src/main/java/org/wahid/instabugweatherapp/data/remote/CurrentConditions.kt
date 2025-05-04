package org.wahid.instabugweatherapp.data.remote



data class CurrentConditions(
    val cloudcover: Int? = null,
    val conditions: String? = null,
    val datetime: String? = null,
    val datetimeEpoch: Int? = null,
    val dew: Int? = null,
    val feelslike: Int? = null,
    val humidity: Double? = null,
    val icon: String? = null,
    val moonphase: Double? = null,
    val precip: Any? = null,
    val precipprob: Int? = null,
    val preciptype: Any? = null,
    val pressure: Int? = null,
    val snow: Int? = null,
    val snowdepth: Int? = null,
    val solarenergy: Int? = null,
    val solarradiation: Int? = null,
    val source: String? = null,
    val stations: List<String?>? = null,
    val sunrise: String? = null,
    val sunriseEpoch: Int? = null,
    val sunset: String? = null,
    val sunsetEpoch: Int? = null,
    val temp: Int? = null,
    val uvindex: Int? = null,
    val visibility: Int? = null,
    val winddir: Int? = null,
    val windgust: Any? = null,
    val windspeed: Double? = null
)