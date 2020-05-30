package com.canlioya.customviewexercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        diagonal_layout_btn.setOnClickListener { launchDiagonalLayoutActivity() }
        dynamic_property_change_btn.setOnClickListener { launchDynamicPropertyChangeExercise() }
    }

    private fun launchDynamicPropertyChangeExercise() {
        val intent = Intent(this, DynamicPropertyChangeActivity::class.java)
        startActivity(intent)
    }

    private fun launchDiagonalLayoutActivity() {
        val intent = Intent(this, DiagonalLayoutActivity::class.java)
        startActivity(intent)
    }

}
