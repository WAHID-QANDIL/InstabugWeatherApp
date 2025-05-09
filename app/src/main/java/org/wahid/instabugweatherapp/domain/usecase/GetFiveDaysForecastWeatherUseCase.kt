package org.wahid.instabugweatherapp.domain.usecase

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.domain.repository.WeatherRepository
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback

class GetFiveDaysForecastWeatherUseCase(
    private val weatherRepositoryImpl: WeatherRepository = AppContainer.weatherRepositoryImpl

) {
    operator fun invoke(
        callback: Callback<List<DomainWeatherDay>>,
    ) {
        weatherRepositoryImpl.getFiveDayForecast(
            callback = callback
        )
    }
}