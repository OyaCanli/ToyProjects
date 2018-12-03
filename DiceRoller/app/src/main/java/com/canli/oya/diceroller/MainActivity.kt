package com.canli.oya.diceroller

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView


class MainActivity : AppCompatActivity() {

    lateinit var dice_iv: ImageView
    private val TAG = "MAinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val roll_btn: Button = findViewById(R.id.roll_btn)
        roll_btn.text = getString(R.string.roll_btn)
        roll_btn.setOnClickListener {
            rollAndAnimateDice()
        }
        dice_iv = findViewById(R.id.dice_image)
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
            dice_iv.setImageResource(resourceId)
        }

        /*Create a local function for animating roll dice:
        Roll the dice three times with small pauses, then stop rolling and begin blinking animation*/
        fun animateDice(initialCount : Int){
            //wait between each roll
            Handler().postDelayed({
                if(initialCount < 2){
                    rollDice(initialCount + 1)
                    animateDice(initialCount + 1)
                } else {
                    //When count reaches 2, stop rolling a start blinking animation
                    val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blinking_anim)
                    dice_iv.startAnimation(animation)
                }
            }, 300)
        }

        //Make a initial roll and then begin rolling with time gaps
        rollDice(0)
        animateDice(0)
    }
}
