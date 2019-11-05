package com.canlioya.textspanexercise

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomTypeFaceSpan(private val font : Typeface?) : MetricAffectingSpan() {
    override fun updateMeasureState(textPaint: TextPaint) = update(textPaint)

    override fun updateDrawState(textPaint: TextPaint) = update(textPaint)

    fun update(textPaint: TextPaint){
        textPaint.apply {
            //Get current style
            val oldStyle = typeface?.style ?: 0
            //Create new font from the given typeface without changing style
            val newFont = Typeface.create(font, oldStyle)
            //Change the typeface
            typeface = newFont
        }
    }


}