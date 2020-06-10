package com.canlioya.customviewexercise.diagonallayout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.core.view.children

class DiagonalLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    val margin = 50

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        val itemSize = getChildAt(0).measuredWidth
        val width =  itemSize * 2 + margin
        val height = itemSize * childCount
        val finalWidth = resolveSize(width, widthMeasureSpec)
        val finalHeight = resolveSize(height, heightMeasureSpec)
        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val firstItem = getChildAt(0)
        val childWidth = firstItem.measuredWidth

        children.forEachIndexed { index, child ->
            val topCoordinate = childWidth * index
            val leftCoordinate= if(index % 2 == 0) 0 else childWidth + margin

            child.layout(leftCoordinate, topCoordinate, leftCoordinate + childWidth, topCoordinate + childWidth)
        }

    }
}