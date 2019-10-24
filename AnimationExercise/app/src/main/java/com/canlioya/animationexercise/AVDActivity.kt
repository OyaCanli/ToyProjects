package com.canlioya.animationexercise

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_avds.*

class AVDActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avds)

        val tomatoAvd : AnimatedVectorDrawable = tomato_image.drawable as AnimatedVectorDrawable
        animate_tomato.setOnClickListener {  tomatoAvd.start() }

        val ballAvd : AnimatedVectorDrawable = ball_image.drawable as AnimatedVectorDrawable
        animate_ball.setOnClickListener {  ballAvd.start() }
    }
}
