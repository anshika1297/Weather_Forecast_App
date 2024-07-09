package com.avidus.weatherforecast.network.api

import com.avidus.weatherforecast.data.RemoteLocation
import com.avidus.weatherforecast.data.RemoteWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    companion object{
        const val BASE_URL = "https://api.weatherapi.com/v1/"
        const val API_KEY ="5cb4cad8fd9e4db9af1151008240807"
    }

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") query: String
    ) : Response<List<RemoteLocation>>

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String = API_KEY,
        @Query("q") query: String,

    ): Response<RemoteWeatherData>
}