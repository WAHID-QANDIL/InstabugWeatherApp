package org.wahid.instabugweatherapp.data.remote



data class RemoteDataModel(
    val address: String? = null,
    val currentConditions: CurrentConditions? = null,
    val days: List<Day?>? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val queryCost: Int? = null,
    val resolvedAddress: String? = null,
    val stations: Stations? = null,
    val timezone: String? = null,
    val tzoffset: Int? = null
)