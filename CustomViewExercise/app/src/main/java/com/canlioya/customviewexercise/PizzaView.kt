package com.canlioya.customviewexercise

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class PizzaView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val defaultStrokeWidth = 6f
    val numberOfPieces = 6

    val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = defaultStrokeWidth
        color = Color.GREEN
    }

    override fun onDraw(canvas: Canvas?) {
        val width = width - paddingStart - paddingEnd
        val height = height - paddingTop - paddingBottom
        val centerX = (width / 2 + paddingStart).toFloat()
        val centerY = (height / 2 + paddingEnd).toFloat()
        val radius = (width.coerceAtMost(height) - defaultStrokeWidth) / 2
        canvas?.drawCircle(centerX, centerY, radius, paint)
        drawPizzaCuts(canvas, centerX, centerY, radius)
    }

    fun drawPizzaCuts(canvas : Canvas?, cX : Float, cY : Float, radius : Float){
        val rotationAngle = 360f / numberOfPieces
        canvas?.save()
        for( i in 0 until numberOfPieces){
            canvas?.rotate(rotationAngle, cX, cY)
            canvas?.drawLine(cX, cY, cX, cY + radius, paint)
        }
        canvas?.restore()
    }
}