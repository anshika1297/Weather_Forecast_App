package com.avidus.weatherforecast.dependency_injection

import com.avidus.weatherforecast.network.repository.WeatherDataRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherDataRepository(weatherAPI = get()) }

}