package org.wahid.instabugweatherapp.presentation.ui.screens.home


import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wahid.instabugweatherapp.domain.location.LocationProvider
import org.wahid.instabugweatherapp.domain.model.DomainWeatherDay
import org.wahid.instabugweatherapp.domain.usecase.GetCurrentWeatherUseCase
import org.wahid.instabugweatherapp.utils.Callback
import java.lang.IllegalArgumentException

class HomeViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = GetCurrentWeatherUseCase(),
) : ViewModel() {

    private val _uiState = MutableLiveData<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState
    private val tracker = LocationProvider()

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun onPermissionGranted() {
        try {
            tracker.startListening { loc ->
                Log.d("onPermissionGranted", "onPermissionGranted:${loc.latitude} ## ${loc.longitude} ")
                getCurrentWeather(loc.latitude, loc.longitude)
            }
        }catch (e: IllegalArgumentException){
            throw e
        }

    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun refresh() {
        Log.d("refresh", "refresh: refreshed")
        onPermissionGranted()
    }

    private fun getCurrentWeather(
        lat: Double,
        lon: Double,
    ) {
        getCurrentWeatherUseCase.invoke(
            lat = lat,
            lon = lon,
            callback = object : Callback<DomainWeatherDay> {
                override fun onSuccess(result: DomainWeatherDay) {
                    _uiState.postValue(HomeUiState.Success(result))
                }

                override fun onError(error: Throwable) {
                    _uiState.postValue(HomeUiState.Error(error))
                }

            }
        )
    }

    override fun onCleared() {
        tracker.stopListening()
        super.onCleared()
    }

    fun onStopUpdates() {
        Log.d("onStopUpdates", "onStopUpdates: onStopUpdates triggered")
        tracker.stopListening()
    }


}