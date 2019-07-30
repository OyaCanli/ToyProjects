package com.canli.oya.diceroller

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.IdlingResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    var sum : Int = 7
    var mediaPlayer : MediaPlayer? = null
    private var idlingResource : SimpleIdlingResource? = null

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

    private fun rollAndAnimateDice() {
        clearPreviousResults()

        //Set idling resource not-idle until animations are completed
        idlingResource?.setIdleState(false)

        //make three rolls with time gaps, to create a rolling feeling
        GlobalScope.launch {
            animateDice()
            //In the end, show a blinking animation to signify that rolling ended
            val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blinking_anim)
            first_dice.startAnimation(animation)
            second_dice.startAnimation(animation)
        }
    }

    private suspend fun animateDice() {
        withContext(Dispatchers.Main){
            for (i in 0..2) {
                rollDice(i)
                delay(DELAY_TIME)
            }
        }
    }

    private fun rollDice(count: Int) {
        //Get a pair of random dice from application(this is extracted for testability)
        val app = application as DiceRollerApplication
        val (firstDie, secondDie) = app.provideDice()
        //Set corresponding drawables for the dice
        first_dice.setImageResource(getDrawableForTheDie(firstDie))
        second_dice.setImageResource(getDrawableForTheDie(secondDie))

        if(count == 2){
            setFinalDiceAsTexts(firstDie, secondDie)
            idlingResource?.setIdleState(true)
        }
    }

    private fun clearPreviousResults(){
        result_tv.text = null
        result_icon.setImageResource(0)
    }

    private fun setFinalDiceAsTexts(firstDie: Int, secondDie : Int){
        sum = firstDie + secondDie
        first_number.text = firstDie.toString()
        second_number.text = secondDie.toString()
    }

    private fun getDrawableForTheDie(count : Int) : Int {
        return when (count) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }

    private fun verifySum() {
        val answer : Int?
        try {
           answer = sum_et.text.toString().toInt()
        } catch (e : NumberFormatException){
            println(e)
            Toast.makeText(this, getString(R.string.no_input_warning), Toast.LENGTH_SHORT).show()
            return
        }

        sum_et.text = null
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(this.currentFocus!!.windowToken, HIDE_NOT_ALWAYS)
         if(sum == answer){
             result_tv.text = getText(R.string.correct_answer)
             result_icon.setImageResource(R.drawable.ic_check)
             startAudioMessage(R.raw.bravo)
         } else {
             result_tv.text = getText(R.string.wrong_answer)
             result_icon.setImageResource(R.drawable.ic_wrong)
             startAudioMessage(R.raw.essaye_encore)
         }
    }

    private fun startAudioMessage(@RawRes mediaId : Int) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, mediaId)
        mediaPlayer?.start()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val DELAY_TIME  = 200L
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }


    @VisibleForTesting
    fun getIdlingResource(): IdlingResource {
        return idlingResource ?: SimpleIdlingResource()
            .also { idlingResource = SimpleIdlingResource() }
    }
}
