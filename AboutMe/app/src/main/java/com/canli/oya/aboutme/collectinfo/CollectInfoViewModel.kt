package com.canli.oya.aboutme.collectinfo

import android.arch.lifecycle.ViewModel

const val totalStepCount: Int = 5

class CollectInfoViewModel : ViewModel() {

    var userInfo = arrayOfNulls<String>(totalStepCount)

    val name: Int = 0
    val nick: Int = 1
    val mail: Int = 2
    val bio: Int = 3
    val imageUrl: Int = 4

}