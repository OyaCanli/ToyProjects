package com.canli.oya.diceroller

const val sampleFirstDice = 5
const val drawableIdForFirstDice = R.drawable.dice_5

const val sampleSecondDice = 3
const val drawableIdForSecondDice = R.drawable.dice_3


fun getSampleDicePair(): Pair<Int, Int> {
    return Pair(sampleFirstDice, sampleSecondDice)
}
