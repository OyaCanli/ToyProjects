package com.canlioya.textspanexercise

import android.text.style.ClickableSpan
import android.view.View
import android.widget.Toast

class CustomClickableSpan : ClickableSpan() {

    override fun onClick(view: View) {
        Toast.makeText(view.context, "Text is clicked", Toast.LENGTH_LONG).show()
    }
}