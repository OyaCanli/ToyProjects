package com.canli.oya.diceroller

import android.app.Application

open class DiceRollerApplication : Application() {

    open fun provideDice() : Pair<Int, Int>{
        return getRandomDicePair()
    }
}