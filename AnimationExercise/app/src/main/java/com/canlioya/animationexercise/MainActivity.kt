package com.canlioya.animationexercise

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val xScaleAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(textView, View.SCALE_X, 2f)
    }

    private val yScaleAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(textView, View.SCALE_Y, 2f)
    }

    private val colorAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofObject(textView, "textColor", ArgbEvaluator(), Color.WHITE, Color.GREEN)
    }

    private val yTranslateAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, 50f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRepeatModeOnAnimator(xScaleAnimator, yScaleAnimator, colorAnimator, yTranslateAnimator)

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(xScaleAnimator, yScaleAnimator, colorAnimator, yTranslateAnimator)
        animatorSet.duration = 1000

        button.setOnClickListener { animatorSet.start() }
    }

    private fun setRepeatModeOnAnimator(vararg animators : ObjectAnimator) {
        for(animator in animators) {
            animator.repeatCount = 5
            animator.repeatMode = ValueAnimator.REVERSE
        }

    }

}
