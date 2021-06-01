package com.example.bondgadgets.common

import java.text.SimpleDateFormat
import java.util.*

private val dateFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
fun Date.toFormattedString() = dateFormatter.format(this)