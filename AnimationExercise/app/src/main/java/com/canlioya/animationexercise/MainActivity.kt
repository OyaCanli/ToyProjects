package com.canlioya.animationexercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tour_image.setOnClickListener{
            val intent = Intent(this@MainActivity, TransformsActivity::class.java)
            val options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, tour_image, "imageTransformPage")
            startActivity(intent, options.toBundle())
        }

        ball_image.setOnClickListener{
            val intent = Intent(this@MainActivity, TransitionActivity::class.java)
            val options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity, ball_image, "transitionsPage")
            startActivity(intent, options.toBundle())
        }

        go_to_avds.setOnClickListener{
            val intent = Intent(this@MainActivity, AVDActivity::class.java)
            startActivity(intent)
        }
    }
}
