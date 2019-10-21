package com.canlioya.animationexercise

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var secondLayoutActive = false

    private val transitionManager by lazy {
        TransitionInflater.from(this@MainActivity).inflateTransitionManager(R.transition.transition_manager, root_main)
    }

    private val scene1 by lazy {
        Scene.getSceneForLayout(root_main, R.layout.activity_main, this@MainActivity)
    }

    private val scene2 by lazy {
        Scene.getSceneForLayout(root_main, R.layout.layout_second, this@MainActivity)
    }

    private val animatorSet : AnimatorSet by lazy {
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
                transitionManager.transitionTo(scene2)
                secondLayoutActive = true
            }
        })

        button.setOnClickListener {
            animatorSet.start()
        }
    }

    override fun onBackPressed() {
        if(secondLayoutActive) {
            transitionManager.transitionTo(scene1)
            secondLayoutActive = false
        } else {
            super.onBackPressed()
        }
    }

}
