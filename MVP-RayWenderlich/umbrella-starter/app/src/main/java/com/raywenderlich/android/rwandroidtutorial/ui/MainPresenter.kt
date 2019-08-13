package com.raywenderlich.android.rwandroidtutorial.ui

import com.raywenderlich.android.rwandroidtutorial.di.DependencyInjector
import com.raywenderlich.android.rwandroidtutorial.model.Weather
import com.raywenderlich.android.rwandroidtutorial.model.WeatherRepository
import com.raywenderlich.android.rwandroidtutorial.model.WeatherState

class MainPresenter(view: MainContract.View,
                    dependencyInjector: DependencyInjector)
    : MainContract.Presenter {

    private val weatherRepository: WeatherRepository = dependencyInjector.weatherRepository()


    private var view: MainContract.View? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun onViewCreated() {
        loadWeather()
    }

    override fun onLoadWeatherTapped() {
        loadWeather()
    }

    private fun loadWeather() {
        val weather = weatherRepository.loadWeather()
        val weatherState = weatherStateForWeather(weather)

        // Make sure to call the displayWeatherState on the view
        view?.displayWeatherState(weatherState)
    }

    private fun weatherStateForWeather(weather: Weather): WeatherState {
        if (weather.rain!!.amount!! > 0) {
            return WeatherState.RAIN
        }
        return WeatherState.SUN
    }
}