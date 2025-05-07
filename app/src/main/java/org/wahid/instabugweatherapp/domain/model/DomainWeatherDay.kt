package org.wahid.instabugweatherapp.domain.model

data class DomainWeatherDay(
    val date:           String,
    val temp:           Double,
    val maxTemp:        Double,
    val minTemp:        Double,
    val description:    String,
    val address:        String,
    val timezone:       String,
    val pressure:       Double,
)