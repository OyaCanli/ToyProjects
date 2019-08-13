package com.raywenderlich.android.rwandroidtutorial.di

import com.raywenderlich.android.rwandroidtutorial.model.WeatherRepository
import com.raywenderlich.android.rwandroidtutorial.model.WeatherRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun weatherRepository() : WeatherRepository {
       return WeatherRepositoryImpl()
    }
}