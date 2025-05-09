package org.wahid.instabugweatherapp.presentation.ui.screens.forcast

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay

sealed class ForecastUiState{
    data object Loading: ForecastUiState()
    data class Success(val result: List<DomainWeatherDay>): ForecastUiState()
    data class Error(val message: String): ForecastUiState()
}
