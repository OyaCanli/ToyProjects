package com.canlioya.emotionalface_raywenderlich

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

private const val HAPPINESS_STATE = "happinessState"

class MainActivity : AppCompatActivity() {

    private var happinessState : Long = EmotionalFaceView.HAPPY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        happyButton.setOnClickListener{
            emotionalFaceView.happinessState = EmotionalFaceView.HAPPY
            happinessState = EmotionalFaceView.HAPPY
        }

        sadButton.setOnClickListener{
            emotionalFaceView.happinessState = EmotionalFaceView.SAD
            happinessState = EmotionalFaceView.SAD
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(HAPPINESS_STATE, happinessState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        happinessState = savedInstanceState.getLong(HAPPINESS_STATE)
        emotionalFaceView.happinessState = happinessState
    }
}
