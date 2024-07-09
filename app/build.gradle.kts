plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.avidus.weatherforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.avidus.weatherforecast"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.media3.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //scalable size units (support for different screens size

    implementation(libs.sdp.android)
    implementation("com.intuit.ssp:ssp-android:1.0.6") // Corrected

    //Navigation Component

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)


    //kotlin (dependency Injection)

    implementation(libs.koin.android)

    //Retrofit

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //ViewModels

    implementation(libs.androidx.lifecycle.viewmodel.ktx) // Corrected

    //Coil

    implementation(libs.coil)

    //Location

    implementation(libs.play.services.location)

    //Swipe refresh Layout

    implementation(libs.androidx.swiperefreshlayout)
}