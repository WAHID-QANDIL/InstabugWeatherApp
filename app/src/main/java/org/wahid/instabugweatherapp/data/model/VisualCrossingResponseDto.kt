package org.wahid.instabugweatherapp.data.model

import android.location.Geocoder
import org.wahid.instabugweatherapp.Application
import java.util.Locale

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