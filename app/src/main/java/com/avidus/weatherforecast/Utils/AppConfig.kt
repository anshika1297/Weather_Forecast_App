package com.avidus.weatherforecast.Utils

import android.app.Application
import com.avidus.weatherforecast.dependency_injection.networkModule
import com.avidus.weatherforecast.dependency_injection.repositoryModule
import com.avidus.weatherforecast.dependency_injection.serializerModule
import com.avidus.weatherforecast.dependency_injection.storageModule
import com.avidus.weatherforecast.dependency_injection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class AppConfig: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
          androidContext(this@AppConfig)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                   serializerModule,
                    storageModule,
                    networkModule
                )
            )

        }
    }

}