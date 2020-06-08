package com.canlioya.customviewexercise

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class DonutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var squareSize = 200
    private var centerY = 100f
    private var centerX = 100f

    private val basePaint : Paint = Paint().apply {
        style = Paint.Style.FILL
        color = 0xFFc6853b.toInt()
    }
    private var holePath = Path()

    private val icingEffect = ComposePathEffect(CornerPathEffect(20f),
        DiscretePathEffect(40f, 20f))

    private val icingPaint = Paint().apply {
        style = Paint.Style.FILL
        color = 0xff48281B.toInt()
        pathEffect = icingEffect
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthAfterMargin = (measuredWidth - paddingStart - paddingEnd)
        centerX = widthAfterMargin / 2f

        val heightAfterMargin = (measuredHeight - paddingTop - paddingBottom)
        centerY = heightAfterMargin /2f

        squareSize = widthAfterMargin.coerceAtMost(heightAfterMargin)

        holePath.apply {
            reset()
            addCircle(centerX, centerY, squareSize / 6f, Path.Direction.CW)
        }

        val finalWidth = ViewGroup.resolveSize(squareSize, widthMeasureSpec)
        val finalHeight = ViewGroup.resolveSize(squareSize, heightMeasureSpec)
        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.save()
        if (Build.VERSION.SDK_INT >= 26){
            canvas?.clipOutPath(holePath)
        } else {
            canvas?.clipPath(holePath, Region.Op.DIFFERENCE)
        }
        canvas?.drawCircle(centerX, centerY, width/2f, basePaint)
        canvas?.drawCircle(centerX, centerY, width / 2.15f, icingPaint)

        canvas?.restore()
    }
}