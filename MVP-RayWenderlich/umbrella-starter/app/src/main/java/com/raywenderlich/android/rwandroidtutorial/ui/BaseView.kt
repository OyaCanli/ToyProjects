package com.raywenderlich.android.rwandroidtutorial.ui

interface BaseView<T> {
    fun setPresenter(presenter : T)
}