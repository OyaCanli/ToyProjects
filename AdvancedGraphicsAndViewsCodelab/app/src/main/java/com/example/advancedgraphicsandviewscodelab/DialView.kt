package com.example.advancedgraphicsandviewscodelab

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class DialView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val SELECTION_COUNT = 4 // Total number of selections.

    private var mWidth = 0f// Custom view width.

    private var mHeight = 0f// Custom view height.

    var fanOnColor = Color.CYAN;
    var fanOffColor = Color.GRAY;

    // For text in the view.
    private var mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        textAlign = Paint.Align.CENTER
        textSize = 40f
    }

    // For dial circle in the view.
    private var dialPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
    }

    private var radius = 0f // Radius of the circle.

    private var activeSelection = 0 // The active selection.

    // String buffer for dial labels and float for ComputeXY result.
    private val mTempLabel = StringBuffer(8)

    init {
        attrs?.let {
            val typedArray = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.DialView,
                0, 0
            )
            // Set the fan on and fan off colors from the attribute values.
            fanOnColor = typedArray.getColor(R.styleable.DialView_fanOnColor, fanOnColor)
            fanOffColor = typedArray.getColor(R.styleable.DialView_fanOffColor, fanOffColor)
            typedArray.recycle()
        }

        dialPaint.color = fanOffColor

        setOnClickListener { // Rotate selection to the next valid choice.
            activeSelection = (activeSelection + 1) % SELECTION_COUNT
            // Set dial background color to green if selection is >= 1.
            if (activeSelection >= 1) {
                dialPaint.color = fanOnColor
            } else {
                dialPaint.color = fanOffColor
            }
            // Redraw the view.
            invalidate()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Calculate the radius from the width and height.
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        radius = (mWidth.coerceAtMost(mHeight) / 2 * 0.8).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val availableWidth = MeasureSpec.getSize(widthMeasureSpec) - paddingStart - paddingEnd
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val availableHeight = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val boxSize = availableWidth.coerceAtMost(availableHeight)

        val finalWidth = MeasureSpec.makeMeasureSpec(boxSize + paddingStart + paddingEnd, widthMode)
        val finalHeight = MeasureSpec.makeMeasureSpec(boxSize + paddingTop + paddingBottom, heightMode)
        setMeasuredDimension(finalWidth, finalHeight)
    }

    private fun computeXYForPosition(pos: Int, radius: Float): Pair<Float, Float> {
        val startAngle = Math.PI * (9 / 8.0) // Angles are in radians.
        val angle = startAngle + pos * (Math.PI / 4)
        val x = (radius * cos(angle)).toFloat() + mWidth / 2
        val y = (radius * sin(angle)).toFloat() + mHeight / 2
        return x to y
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the dial.
        canvas.drawCircle(mWidth / 2, mHeight / 2, radius, dialPaint)
        // Draw the text labels.
        val labelRadius = radius + 20
        val label = mTempLabel
        for (i in 0 until SELECTION_COUNT) {
            val xyData = computeXYForPosition(i, labelRadius)
            val (x, y) = xyData
            label.setLength(0)
            label.append(i)
            canvas.drawText(label, 0, label.length, x, y, mTextPaint)
        }
        // Draw the indicator mark.
        val markerRadius = radius - 35
        val xyData = computeXYForPosition(
            activeSelection,
            markerRadius
        )
        val (x, y) = xyData
        canvas.drawCircle(x, y, 20f, mTextPaint)
    }
}