package com.avidus.weatherforecast.dependency_injection

import com.avidus.weatherforecast.storage.SharedPreferenceManager
import org.koin.dsl.module

val storageModule = module {
    single { SharedPreferenceManager(context =get(), gson =get()) }

}