package com.raywenderlich.android.rwandroidtutorial.ui

import com.raywenderlich.android.rwandroidtutorial.model.WeatherState

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onLoadWeatherTapped()
    }

    interface View : BaseView<Presenter> {
        fun displayWeatherState(weatherState: WeatherState)
    }
}