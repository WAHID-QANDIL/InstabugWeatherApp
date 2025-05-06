package org.wahid.instabugweatherapp.utils

sealed class WeatherUnits(unit:String){
    data object metric: WeatherUnits(unit = "metric")
    data object us: WeatherUnits(unit = "us")
    data object uk: WeatherUnits(unit = "uk")
}