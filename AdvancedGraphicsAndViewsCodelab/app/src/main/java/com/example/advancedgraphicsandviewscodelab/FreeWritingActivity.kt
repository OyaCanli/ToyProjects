package com.example.advancedgraphicsandviewscodelab

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FreeWritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val freeWritingView = FreeWritingView(this).apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        setContentView(freeWritingView)

    }
}