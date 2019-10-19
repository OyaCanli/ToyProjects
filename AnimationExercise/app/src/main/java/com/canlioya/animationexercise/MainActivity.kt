package com.canlioya.animationexercise

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val xScaleAnimator : ObjectAnimator by lazy {
        ObjectAnimator.ofFloat(textView, View.SCALE_X, 2f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        xScaleAnimator.duration = 1000
        xScaleAnimator.repeatCount = 3
        xScaleAnimator.repeatMode = ValueAnimator.REVERSE
        button.setOnClickListener { xScaleAnimator.start() }
    }

}
