package com.canlioya.animationexercise

import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_avds.*

class AVDActivity : AppCompatActivity() {

    var inCrossShape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avds)

        val tomatoAvd: AnimatedVectorDrawable = tomato_image.drawable as AnimatedVectorDrawable
        animate_tomato.setOnClickListener { tomatoAvd.start() }

        val ballAvd: AnimatedVectorDrawable = ball_image.drawable as AnimatedVectorDrawable
        animate_ball.setOnClickListener { ballAvd.start() }

        plus_to_cross_btn.setOnClickListener {
            val plusToCrossAvd: AnimatedVectorDrawable =
                plus_image.drawable as AnimatedVectorDrawable
            plusToCrossAvd.clearAnimationCallbacks()
            plusToCrossAvd.registerAnimationCallback(AnimOverCallback())
            plusToCrossAvd.start()
        }
    }

    fun changeImageDrawable() {
        if (!inCrossShape) {
            inCrossShape = true
            plus_image.setImageResource(R.drawable.cross_to_plus_avd)
        } else {
            inCrossShape = false
            plus_image.setImageResource(R.drawable.plus_to_cross_avd)
        }
    }

    inner class AnimOverCallback : Animatable2.AnimationCallback() {
        override fun onAnimationStart(drawable: Drawable) {}

        override fun onAnimationEnd(drawable: Drawable) {
            changeImageDrawable() }
    }
}


