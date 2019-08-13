package com.raywenderlich.android.rwandroidtutorial.di

import com.raywenderlich.android.rwandroidtutorial.model.WeatherRepository

interface DependencyInjector {
   fun weatherRepository() : WeatherRepository
}