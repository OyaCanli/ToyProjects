package com.canlioya.draganddropexercise

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnTouchListener, View.OnDragListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnTouchListener(this)
        textView.setOnDragListener(this)

        target.setOnDragListener(this)

    }

    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {
        if (motionEvent?.action == MotionEvent.ACTION_DOWN && (view as? TextView)?.text?.isNotEmpty() == true) {
            val data: ClipData = ClipData.newPlainText("label", view.text)
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDragAndDrop(data, shadowBuilder, view, 0)
            return true
        }
        return false
    }

    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        Log.d("MAIN","Current event : ${event?.action}")
        when(event?.action) {
            DragEvent.ACTION_DROP -> {
                val sourceView = event.localState as? TextView
                sourceView?.text = " "
                (view as TextView).text = event.clipData.getItemAt(0).text
            }
        }

        return true

    }

}
