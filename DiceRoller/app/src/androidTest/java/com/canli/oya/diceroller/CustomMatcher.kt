package com.canli.oya.diceroller

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher


fun withDrawable(@DrawableRes drawableId : Int) : Matcher<View>{

    return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun matchesSafely(view: ImageView): Boolean {
            val expectedBitmap = view.context.getDrawable(drawableId).toBitmap()
            return view.drawable.toBitmap().sameAs(expectedBitmap)
        }

        override fun describeTo(description: Description) {
            description.appendText("with drawable id: $drawableId")
        }
    }
}