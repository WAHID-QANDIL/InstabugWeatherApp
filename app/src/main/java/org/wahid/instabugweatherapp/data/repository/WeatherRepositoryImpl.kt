package org.wahid.instabugweatherapp.data.repository

import org.wahid.instabugweatherapp.data.local.db.model.WeatherDbEntity
import org.wahid.instabugweatherapp.data.local.mediator.Mediator
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.domain.repository.WeatherRepository
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.Callback
import org.wahid.instabugweatherapp.utils.WeatherUnits

class WeatherRepositoryImpl(
    val mediator: Mediator = AppContainer.mediator,
) : WeatherRepository {

    override fun getCurrentWeather(
        lat: Double,
        lon: Double,
        unit: WeatherUnits,
        callback: Callback<DomainWeatherDay>,
    ) {
            mediator.loadFirst(callback = object : Callback<WeatherDbEntity>{
                override fun onSuccess(result: WeatherDbEntity) {
                    callback.onSuccess(result = result.toDomain())
                }
                override fun onError(error: Throwable) {
                    callback.onError(error)
                }
            })

    }

    override fun getFiveDayForecast(
        lat: Double,
        lon: Double,
        unit: WeatherUnits,
        callback: Callback<List<DomainWeatherDay>>,
    ) {
        mediator.loadFetchAll(
            lat = lat,
            lon = lon,
            callback = object : Callback<List<WeatherDbEntity>> {
                override fun onSuccess(result: List<WeatherDbEntity>) {
                    val res = result.map {
                        it.toDomain()
                    }
                    callback.onSuccess(res)
                }

                override fun onError(error: Throwable) {
                    callback.onError(error)
                }

            })
    }


}