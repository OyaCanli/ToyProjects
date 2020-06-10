package com.canlioya.customviewexercise.carousellayoutmanager

import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.canlioya.customviewexercise.R
import kotlinx.android.synthetic.main.activity_carousel_layout_manager.*

val sampleList = listOf("banana", "apple", "pear", "strawberry", "cherry", "plum", "orange", "kiwi", "kumquat", "wolfberry", "dragonfruit")

class CustomLayoutManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousel_layout_manager)

        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val screenWidth = size.x

        list.layoutParams.height = screenWidth / 2

        list.adapter =
            SimpleListAdapter(
                sampleList
            )
        list.layoutManager =
            CarouselLayoutManager(
                resources,
                screenWidth
            )
    }


}