package org.wahid.instabugweatherapp.domain.location

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import org.wahid.instabugweatherapp.utils.AppContainer
import org.wahid.instabugweatherapp.utils.AppExecutors

class LocationProvider(
    context: Context = AppContainer.appContext,
    private val minTimeMs: Long = 5_000L,
    private val minDistanceM: Float = 10f,
) {
    val locationManager = (context.getSystemService(Context.LOCATION_SERVICE)) as LocationManager
    private var locationListener: LocationListener? = null


    @RequiresPermission(allOf = [permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION])
    fun startListening(
        onLocation: (Location) -> Unit,
    ): Boolean {


        val provider =  when {
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) -> LocationManager.GPS_PROVIDER
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) -> LocationManager.NETWORK_PROVIDER
            else -> throw IllegalArgumentException("The GPS provider is not enabled")
        }

        val fineGranted = ContextCompat.checkSelfPermission(
            AppContainer.appContext,
            permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val coarseGranted = ContextCompat.checkSelfPermission(
            AppContainer.appContext,
            permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!(fineGranted || coarseGranted)) {
            // Caller must request permissions first
            return false
        }

        stopListening()
        locationListener = object : LocationListener {
            override fun onLocationChanged(loc: Location) = onLocation(loc)
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {
                stopListening()
            }
        }


        locationManager.requestLocationUpdates(
            provider,
            minTimeMs,
            minDistanceM,
            locationListener!!,
            AppExecutors.locationLooper
        )
        return true


    }

    fun stopListening() {
        locationListener?.let {
            locationManager.removeUpdates(it)
            locationListener = null
        }
    }
}