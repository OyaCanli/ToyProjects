package com.canlioya.animationexercise

import android.graphics.Point
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation


class PhysicsAnimsActivity : AppCompatActivity() {

    val velocity = 5000f
    var ballDirectionMap = mutableMapOf(R.id.ball1 to 1, R.id.ball2 to 1, R.id.ball3 to 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physics_anims)
    }

    fun initFlingAnimation(view: View) {
        val direction = ballDirectionMap[view.id] ?: 0
        val screenHeight = getScreenHeight()
        val viewHeightInPixels = convertDpToPx(view.height.toFloat())
        val fling = FlingAnimation(view, DynamicAnimation.Y)
        fling.apply {
            setMinValue(0f)
            setMaxValue(screenHeight - viewHeightInPixels)
            addEndListener { _, _, _, _ ->
                ballDirectionMap[view.id] = ballDirectionMap[view.id]?.times(-1) ?: 1
            }
            setStartVelocity(velocity * direction)
            start()
        }
    }

    private fun getScreenHeight(): Float {
        val point = Point()
        windowManager.defaultDisplay.getSize(point)
        return point.y.toFloat()
    }

    fun convertDpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }
}
