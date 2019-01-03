package com.canli.oya.aboutme.collectinfo

import android.arch.lifecycle.ViewModel

class CollectInfoViewModel : ViewModel() {

    var name: String? = null
    var nick: String? = null
    var mail: String? = null
    var bio: String? = null
    var imageUrl: String? = null

    var stepNumber: Int = 0

}