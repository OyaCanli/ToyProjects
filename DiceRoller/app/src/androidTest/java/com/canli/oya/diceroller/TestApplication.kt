package com.canli.oya.diceroller

class TestApplication : DiceRollerApplication(){

    override fun provideDice() : Pair<Int, Int>{
        return getSampleDicePair()
    }
}