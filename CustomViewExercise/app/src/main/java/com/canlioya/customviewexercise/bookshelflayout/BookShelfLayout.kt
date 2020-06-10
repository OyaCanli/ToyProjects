package com.canlioya.customviewexercise.bookshelflayout

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.LinearLayout

class BookShelfLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //Swap height and width
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(measuredHeight, measuredWidth)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.save()
        canvas?.translate(0f, height.toFloat())
        canvas?.rotate(-90f)
        super.dispatchDraw(canvas)
        canvas?.restore()
    }
}