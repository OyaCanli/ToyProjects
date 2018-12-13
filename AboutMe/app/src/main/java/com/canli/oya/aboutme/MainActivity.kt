package com.canli.oya.aboutme

import android.content.Context
import android.hardware.input.InputManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    lateinit var step_info_tv: TextView
    lateinit var user_input_et: EditText
    lateinit var name_tv: TextView
    lateinit var nick_tv: TextView
    lateinit var bio_tv: TextView

    private var stepNumber : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        step_info_tv = findViewById(R.id.step_info_tv)
        user_input_et = findViewById(R.id.user_input_et)
        name_tv = findViewById(R.id.name_tv)
        nick_tv = findViewById(R.id.nickname_tv)
        bio_tv = findViewById(R.id.details_tv)

        findViewById<Button>(R.id.done_btn).setOnClickListener {
            saveInput(it)
        }
    }

    private fun saveInput(view: View){

        fun setNext(tv: TextView, nextStep: String){
           tv.text = user_input_et.text
            user_input_et.text = null
            tv.visibility = View.VISIBLE
            step_info_tv.text = "Enter your $nextStep"
        }

        //For hiding soft keyboard
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)

        when(stepNumber){
            0 -> setNext(name_tv, "nickname")
            1 -> setNext(nick_tv, "biography")
            3 -> setNext(bio_tv, "")
        }

        stepNumber++
    }
}
