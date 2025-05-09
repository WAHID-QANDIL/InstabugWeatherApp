package org.wahid.instabugweatherapp.presentation.ui.screens.home

import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val currentWeather: DomainWeatherDay) : HomeUiState()
    data class Error(val error: Throwable) : HomeUiState()
}