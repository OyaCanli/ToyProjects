package com.canlioya.animationexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var angle = 10f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            runRotateAnimation()
        }
    }

    private fun runRotateAnimation() {
        angle *= -1
        textView.animate()
            .rotationBy(angle)
            .setDuration(100)
            .withEndAction{
                runRotateAnimation()
            }
    }

}
