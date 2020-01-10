package com.canli.oya.aboutme.showinfo

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ShowProfileViewModelFactory(val userInfo: Array<out String?>?) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShowProfileViewModel(userInfo) as T
    }
}