package com.canli.oya.diceroller

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_btn.setOnClickListener {
            rollAndAnimateDice()
        }
    }

    private fun rollAndAnimateDice() {

        val diceRange = mutableListOf(1,2,3,4,5,6)
        diceRange.shuffle()
        Log.d(TAG, "diceRange: $diceRange")

        /*Create a local function for rolling the dice:
        Pick a random int and set the corresponding dice image*/
        fun rollDice(count : Int) {
            val resourceId: Int = when (diceRange[count]) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            Log.d(TAG, "picked number: ${diceRange[count]}")
            dice_image.setImageResource(resourceId)
        }

        //A local function to make three rolls with time gaps, to create a rolling feeling
        suspend fun animateDice() {
            for (i in 0..2) {
                rollDice(i)
                delay(DELAY_TIME)
            }
        }

        GlobalScope.launch {
            animateDice()
            //In the end, show a blinking animation to signify that rolling ended
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blinking_anim)
            dice_image.startAnimation(animation)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val DELAY_TIME  = 200L
    }
}
