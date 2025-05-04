package org.wahid.instabugweatherapp.data.remote




data class Day(
    val cloudcover: Double? = null,
    val conditions: String? = null,
    val datetime: String? = null,
    val datetimeEpoch: Int? = null,
    val description: String? = null,
    val dew: Double? = null,
    val feelslike: Double? = null,
    val feelslikemax: Double? = null,
    val feelslikemin: Double? = null,
    val humidity: Double? = null,
    val icon: String? = null,
    val moonphase: Double? = null,
    val precip: Int? = null,
    val precipcover: Int? = null,
    val precipprob: Int? = null,
    val preciptype: Any? = null,
    val pressure: Double? = null,
    val severerisk: Int? = null,
    val snow: Int? = null,
    val snowdepth: Int? = null,
    val solarenergy: Double? = null,
    val solarradiation: Double? = null,
    val source: String? = null,
    val stations: List<String?>? = null,
    val sunrise: String? = null,
    val sunriseEpoch: Int? = null,
    val sunset: String? = null,
    val sunsetEpoch: Int? = null,
    val temp: Double? = null,
    val tempmax: Double? = null,
    val tempmin: Double? = null,
    val uvindex: Int? = null,
    val visibility: Double? = null,
    val winddir: Double? = null,
    val windgust: Double? = null,
    val windspeed: Double? = null
)