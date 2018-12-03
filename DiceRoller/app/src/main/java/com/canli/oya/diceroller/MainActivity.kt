package com.canli.oya.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var dice_iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val roll_btn: Button = findViewById(R.id.roll_btn)
        roll_btn.text = "Let's roll"
        roll_btn.setOnClickListener {
            rollDice()
        }
        dice_iv = findViewById(R.id.dice_image)
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(6) + 1
        val resourceId: Int = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice_iv.setImageResource(resourceId)

    }
}
