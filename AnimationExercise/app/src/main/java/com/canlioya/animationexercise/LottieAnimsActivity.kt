package com.canlioya.animationexercise

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lottie_anims.*

class LottieAnimsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_anims)
    }

    fun startLottieAnim(view: View) {
        if(lottieAnim.isAnimating){
            lottieAnim.cancelAnimation()
        } else {
            lottieAnim.playAnimation()
        }
    }
}
