package com.example.advancedgraphicsandviewscodelab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ClipPathActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ClippedView(this))
    }
}