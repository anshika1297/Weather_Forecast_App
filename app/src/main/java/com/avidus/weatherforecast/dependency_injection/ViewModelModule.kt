package com.avidus.weatherforecast.dependency_injection

import com.avidus.weatherforecast.fragments.home.HomeViewModel
import com.avidus.weatherforecast.fragments.location.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(weatherDataRepository = get()) }
    viewModel { LocationViewModel (weatherDataRepository = get()) }
}