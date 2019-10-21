package com.canlioya.animationexercise

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val animatorSet : AnimatorSet by lazy {
        AnimatorInflater.loadAnimator(this@MainActivity, R.animator.rotate) as AnimatorSet}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animatorSet.duration = 2000
        animatorSet.setTarget(ball_image)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Toast.makeText(this@MainActivity, "Animation started", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationRepeat(p0: Animator?) {
                Toast.makeText(this@MainActivity, "Animation is repeated", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                Toast.makeText(this@MainActivity, "Animation ended", Toast.LENGTH_SHORT).show()
            }
        })

        button.setOnClickListener {
            animatorSet.start()
        }

    }
}
