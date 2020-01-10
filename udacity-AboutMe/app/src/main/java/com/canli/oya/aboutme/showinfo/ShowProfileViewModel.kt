package com.canli.oya.aboutme.showinfo

import android.arch.lifecycle.ViewModel

class ShowProfileViewModel(val userInfo: Array<out String?>?) : ViewModel() {
    val name: Int = 0
    val nick: Int = 1
    val mail: Int = 2
    val bio: Int = 3
    val imageUrl: Int = 4
}