package com.example.advancedgraphicsandviewscodelab


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_canvas_example.*


class CanvasExampleActivity : AppCompatActivity() {

    var canvas : Canvas? = null
    val paint = Paint()
    val paintText = Paint(Paint.UNDERLINE_TEXT_FLAG)
    var bitmap : Bitmap? = null
    private val rect = Rect()
    private val bounds = Rect()

    private val OFFSET = 120
    private var mOffset = OFFSET
    private val MULTIPLIER = 100

    private var colorBackground = 0
    private var colorRectangle = 0
    private var colorAccent = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_example)

        colorBackground = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
        colorRectangle = ResourcesCompat.getColor(resources, R.color.colorRectangle, null)
        colorAccent = ResourcesCompat.getColor(resources, R.color.colorAccent, null)

        paint.color = colorBackground

        paintText.apply {
            color = ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null)
            textSize = 70f
        }
    }

    fun drawSomething(view: View) {
        val vWidth = view.width
        val vHeight = view.height
        val halfWidth = vWidth / 2
        val halfHeight = vHeight / 2
        // Only do this first time view is clicked after it has been created.
        if (mOffset == OFFSET) { // Only true once, so don't need separate flag.
            // Each pixel takes 4 bytes, with alpha channel.
            // Use RGB_565 if you don't need alpha and a huge color palette.
            bitmap = Bitmap.createBitmap(
                vWidth, vHeight, Bitmap.Config.ARGB_8888
            )
            // Associate the bitmap to the ImageView.
            rectangles.setImageBitmap(bitmap)
            // Create a Canvas with the bitmap.
            canvas = Canvas(bitmap!!)
            // Fill the entire canvas with this solid color.
            canvas?.drawColor(colorBackground)
            // Draw some text styled with mPaintText.
            canvas?.drawText(getString(R.string.keep_tapping), 100f, 100f, paintText)
            // Increase the indent.
            mOffset += OFFSET
        } else {
            // Draw in response to user action.
            // As this happens on the UI thread, there is a limit to complexity.
            if (mOffset < halfWidth && mOffset < halfHeight) {
                // Change the color by subtracting an integer.
                paint.color = colorRectangle - MULTIPLIER * mOffset
                rect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset)
                canvas?.drawRect(rect, paint)
                // Increase the indent.
                mOffset += OFFSET
            } else {
                paint.color = colorAccent
                canvas?.drawCircle(
                    halfWidth.toFloat(), halfHeight.toFloat(), halfWidth / 3f, paint
                )
                val text = getString(R.string.done)
                // Get bounding box for text to calculate where to draw it.
                paintText.getTextBounds(text, 0, text.length, bounds)
                // Calculate x and y for text so it's centered.
                val x = halfWidth - bounds.centerX()
                val y = halfHeight - bounds.centerY()
                canvas?.drawText(text, x.toFloat(), y.toFloat(), paintText)
                infoText.visibility = View.GONE
            }
        }
        // Invalidate the view, so that it gets redrawn.
        view.invalidate()
    }
}