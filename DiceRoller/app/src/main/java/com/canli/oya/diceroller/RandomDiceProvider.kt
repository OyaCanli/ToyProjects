package com.canli.oya.diceroller

import java.util.*

fun getRandomDicePair() : Pair<Int, Int>{
        val rand = Random()
        val firstDie = rand.nextInt(6) + 1
        val secondDie = rand.nextInt(6) + 1
        return Pair(firstDie, secondDie)
    }
