package org.wahid.instabugweatherapp.domain.usecase

import org.wahid.instabugweatherapp.data.repository.WeatherRepositoryImpl
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback

class GetCurrentWeatherUseCase(
    private val weatherRepositoryImpl: WeatherRepositoryImpl = AppContainer.weatherRepositoryImpl,
) {
    operator fun invoke(
        lat: Double,
        lon: Double,
        callback: Callback<DomainWeatherDay>,
    ) {
        weatherRepositoryImpl.getCurrentWeather(lat = lat, lon = lon, callback = callback)
    }

}