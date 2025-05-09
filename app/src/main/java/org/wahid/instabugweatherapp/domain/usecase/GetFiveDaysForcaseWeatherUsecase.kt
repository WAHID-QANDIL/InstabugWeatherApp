package org.wahid.instabugweatherapp.domain.usecase

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback

class GetFiveDaysForcaseWeatherUsecase {
    private val weatherRepositoryImpl = AppContainer.weatherRepositoryImpl
    operator fun invoke(
        callback: Callback<List<DomainWeatherDay>>,
    ) {
        weatherRepositoryImpl.getFiveDayForecast(
            callback = callback
        )
    }
}