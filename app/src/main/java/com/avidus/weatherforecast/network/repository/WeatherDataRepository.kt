package com.avidus.weatherforecast.network.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import com.avidus.weatherforecast.data.CurrentLocation
import com.avidus.weatherforecast.data.RemoteLocation
import com.avidus.weatherforecast.data.RemoteWeatherData
import com.avidus.weatherforecast.network.api.WeatherAPI
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlin.coroutines.cancellation.CancellationException

class WeatherDataRepository (private val weatherAPI: WeatherAPI){

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        onSuccess: (currentLocation: CurrentLocation) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY, CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            if (location != null) {
                onSuccess(CurrentLocation(latitude = location.latitude, longitude = location.longitude))
            } else {
                onFailure(NullPointerException("Location is null"))
            }
        }.addOnFailureListener { exception ->
            onFailure(exception)
        }
    }
@Suppress("DEPRECATION")
    fun updateAddressText(
        currentLocation: CurrentLocation,
        geocoder: Geocoder
    ): CurrentLocation {
        val latitude = currentLocation.latitude ?: return currentLocation
        val longitude = currentLocation.longitude ?: return currentLocation

        return geocoder.getFromLocation(latitude, longitude, 1)?.let { addresses ->
            val address = addresses[0]
            val addressText = StringBuilder().apply {
                append(address.locality).append(", ")
                append(address.adminArea).append(", ")
                append(address.countryName)
            }.toString()

            currentLocation.copy(location = addressText)
        } ?: currentLocation
    }

    suspend fun searchLocation(query: String): List<RemoteLocation> {
        val response = weatherAPI.searchLocation(query = query)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getWeatherData(latitude: Double, longitude: Double): RemoteWeatherData? {
        val response = weatherAPI.getWeatherData(query = "$latitude,$longitude")
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

}
