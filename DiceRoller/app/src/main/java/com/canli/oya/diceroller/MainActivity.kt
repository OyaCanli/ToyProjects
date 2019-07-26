package com.canli.oya.diceroller

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    var sum : Int = 7
    var mediaPlayer : MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_btn.setOnClickListener {
            rollAndAnimateDice()
        }

        ok_btn.setOnClickListener {
            verifySum()
        }
    }

    private fun verifySum() {
        val answer : Int?
        try {
           answer = sum_et.text.toString().toInt()
        } catch (e : NumberFormatException){
            println(e)
            Toast.makeText(this, "Bir sayi girmediniz", Toast.LENGTH_SHORT).show()
            return
        }

        sum_et.text = null
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(this.currentFocus!!.windowToken, HIDE_NOT_ALWAYS)
         if(sum == answer){
             result_tv.text = getText(R.string.correct_answer)
             result_icon.setImageResource(R.drawable.ic_check)
             mediaPlayer?.release()
             mediaPlayer = MediaPlayer.create(this, R.raw.bravo)
             mediaPlayer?.start()
         } else {
             result_tv.text = getText(R.string.wrong_answer)
             result_icon.setImageResource(R.drawable.ic_wrong)
             mediaPlayer?.release()
             mediaPlayer = MediaPlayer.create(this, R.raw.essaye_encore)
             mediaPlayer?.start()
         }
    }

    private fun rollAndAnimateDice() {
       /* Create a local function for rolling the dice:
        Pick a random int and set the corresponding dice image*/
        fun getDrawableForTheDie(count : Int) : Int {
            return when (count) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }

        val rand = Random()
        val firstDie = rand.nextInt(6) + 1
        val secondDie = rand.nextInt(6) + 1
        first_number.text = firstDie.toString()
        second_number.text = secondDie.toString()
        first_dice.setImageResource(getDrawableForTheDie(firstDie))
        second_dice.setImageResource(getDrawableForTheDie(secondDie))
        sum = firstDie + secondDie
        result_tv.text = null
        result_icon.setImageResource(0)

        /*A local function to make three rolls with time gaps, to create a rolling feeling
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
            first_dice.startAnimation(animation)
        }*/
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val DELAY_TIME  = 200L
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
