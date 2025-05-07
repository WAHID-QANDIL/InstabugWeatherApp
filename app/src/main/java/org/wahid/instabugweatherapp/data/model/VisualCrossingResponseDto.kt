package org.wahid.instabugweatherapp.data.model

@Suppress("DEPRECATION")
data class VisualCrossingResponseDto(
    val queryCost:          Int?,
    val latitude:           Double?,
    val longitude:          Double?,
    val resolvedAddress:    String?,
    val address:            String?,
    val timezone:           String?,
    val tzoffset:           Double?,
    val description:        String?,
    val days:               List<DayWeather>?,
)