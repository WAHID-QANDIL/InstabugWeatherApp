package org.wahid.instabugweatherapp.domain.usecase

import org.wahid.instabugweatherapp.data.repository.WeatherRepositoryImpl
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback

class GetFiveDaysForcaseWeather {
    private val weatherRepositoryImpl = AppContainer.weatherRepositoryImpl
    operator fun invoke(
        lat: Double,
        lon: Double,
        callback: Callback<List<DomainWeatherDay>>,
    ) {
        weatherRepositoryImpl.getFiveDayForecast(
            lat = lat,
            lon = lon,
            callback = callback
        )
    }
}