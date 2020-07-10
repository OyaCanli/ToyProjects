package com.canlioya.animationexercise

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.graphics.Color
import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transition.*

class TransitionActivity : AppCompatActivity() {

    var secondLayoutActive = false

    private val transitionManager by lazy {
        TransitionInflater.from(this@TransitionActivity).inflateTransitionManager(R.transition.transition_manager, root_main)
    }

    private val scene1 by lazy {
        Scene.getSceneForLayout(root_main, R.layout.activity_transition, this@TransitionActivity)
    }

    private val scene2 by lazy {
        Scene.getSceneForLayout(root_main, R.layout.transition_second, this@TransitionActivity)
    }

    private val animatorSet : AnimatorSet by lazy {
        AnimatorInflater.loadAnimator(this@TransitionActivity, R.animator.rotate) as AnimatorSet}

    private var cardFlipped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        animatorSet.duration = 2000
        animatorSet.setTarget(ball_image)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Toast.makeText(this@TransitionActivity, "Animation started", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationRepeat(p0: Animator?) {
                Toast.makeText(this@TransitionActivity, "Animation is repeated", Toast.LENGTH_SHORT).show()
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

        flip_image.setOnClickListener { flipImage() }
    }

    private fun flipImage() {
        val animatorFlipLeftOut = AnimatorInflater.loadAnimator(this@TransitionActivity, R.animator.flip_left_out)
        animatorFlipLeftOut.setTarget(flip_image)

        val animatorFlipRightIn = AnimatorInflater.loadAnimator(this@TransitionActivity, R.animator.flip_left_in)
        animatorFlipRightIn.setTarget(flip_image)

        animatorFlipLeftOut.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                Toast.makeText(this@TransitionActivity, "Animation started", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationRepeat(p0: Animator?) {
                Toast.makeText(this@TransitionActivity, "Animation is repeated", Toast.LENGTH_SHORT).show()
            }
            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                if(cardFlipped){
                    flip_image.setBackgroundResource(R.drawable.maiden_tower)
                    flip_image.text = ""
                } else {
                    flip_image.setBackgroundColor(Color.GRAY)
                    flip_image.text = "MAIDEN TOWER"
                }
                animatorFlipRightIn.start()
                cardFlipped = !cardFlipped
            }
        })
        animatorFlipLeftOut.start()
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
