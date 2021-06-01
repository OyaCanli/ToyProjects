package com.example.bondgadgets

import java.util.*

sealed class Gadget{
    var id : Int = 0
    var dateCreated : Date = Date()
}

data class GadgetQRCode(val url : String) : Gadget()

data class GadgetNFC(val url : String) : Gadget()