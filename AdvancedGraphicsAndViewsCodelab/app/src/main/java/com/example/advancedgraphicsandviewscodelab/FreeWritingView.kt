package com.example.advancedgraphicsandviewscodelab

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import kotlin.math.abs


class FreeWritingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var extraCanvas: Canvas? = null
    private var extraBitmap: Bitmap? = null

    private var frame : Rect? = null
    val inset = 40

    private var mX = 0f
    private  var mY = 0f
    private val TOUCH_TOLERANCE = 4f

    val backgroundColor = ResourcesCompat.getColor(resources, R.color.opaque_orange, null)
    val drawColor = ResourcesCompat.getColor(resources, R.color.opaque_yellow, null)

    // Holds the path we are currently drawing.
    val path = Path()
    // Set up the paint with which to draw.
    val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true // Smoothes out edges of what is drawn without affecting shape.
        isDither = true // Dithering affects how colors with higher-precision device than the are down-sampled.
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = 12f // default: Hairline-width (really thin)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        // Create bitmap, create canvas with bitmap, fill canvas with color.
        extraBitmap = Bitmap.createBitmap(
            width, height,
            Bitmap.Config.ARGB_8888
        )
        extraCanvas = Canvas(extraBitmap!!)
        // Fill the Bitmap with the background color.
        extraCanvas!!.drawColor(backgroundColor)

        frame = Rect(inset, inset, width - inset, height - inset)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the bitmap that stores the path the user has drawn.
        // Initially the user has not drawn anything
        // so we see only the colored bitmap.
        canvas.drawBitmap(extraBitmap!!, 0f, 0f, null)

        // Draw a frame around the picture.
        frame?.let {
            canvas.drawRect(it, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(x, y)
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> touchUp()
            else -> {
            }
        }
        return true
    }

    private fun touchStart(x: Float, y: Float) {
        path.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = abs(x - mX)
        val dy = abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            // Reset mX and mY to the last drawn point.
            mX = x
            mY = y
            // Save the path in the extra bitmap,
            // which we access through its canvas.
           extraCanvas?.drawPath(path, paint)
        }
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        path.reset()
    }

}