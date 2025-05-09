package org.wahid.instabugweatherapp.presentation.ui.screens.forcast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.domain.usecase.GetFiveDaysForecastWeatherUseCase
import org.wahid.instabugweatherapp.utils.Callback

class ForecastScreenViewModel(
    val getFiveDaysForcaseWeatherUsecase: GetFiveDaysForecastWeatherUseCase = GetFiveDaysForecastWeatherUseCase(),
) :
    ViewModel() {

    private val _uiState = MutableLiveData<ForecastUiState>(ForecastUiState.Loading)
    val uiState = _uiState


    private fun getFiveForcast(){
        getFiveDaysForcaseWeatherUsecase.invoke(
            callback = object :Callback<List<DomainWeatherDay>>{
                override fun onSuccess(result: List<DomainWeatherDay>) {
                    _uiState.postValue(ForecastUiState.Success(result))
                }

                override fun onError(error: Throwable) {
                    _uiState.postValue(ForecastUiState.Error(error.message.toString()))
                }

            }
        )
    }
    fun onReLoad(){
        getFiveForcast()
    }

    init {
        getFiveForcast()
    }

}