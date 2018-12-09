package com.canli.oya.aboutme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var user_input_et: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user_input_et = findViewById(R.id.user_input_et)

    }
}
