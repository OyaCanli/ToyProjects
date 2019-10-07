package com.canlioya.customviewexercise

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        increasePadding.setOnClickListener(this)
        swapColor.setOnClickListener(this)
        decreasePadding.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.increasePadding -> {
                myCustomView.customPaddingUp(30)
            }
            R.id.swapColor -> {
                myCustomView.swapColor()
            }
            R.id.decreasePadding -> {
                myCustomView.customPaddingDown(30)
            }
        }
    }
}
