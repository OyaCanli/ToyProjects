package com.canlioya.customviewexercise

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList

class DonutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var squareSize = 200
    private var centerY = 100f
    private var centerX = 100f

    private val basePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xFFc6853b.toInt()
    }
    private var holePath = Path()

    private val icingEffect = ComposePathEffect(
        CornerPathEffect(20f),
        DiscretePathEffect(40f, 20f)
    )

    private val icingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xff48281B.toInt()
        pathEffect = icingEffect
    }

    private val sprinkleColors = intArrayOf(
        Color.RED, Color.WHITE,
        Color.YELLOW, Color.BLUE,
        Color.CYAN, Color.GREEN, Color.MAGENTA
    )

    private var sprinkles : List<Sprinkle> = ArrayList()

    private val sprinklePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var sprinkleCount: Int = 50

    private var sprinkleWidth = 14f
    private var sprinkleHeight = 42f

    private val sprinklePadding = 20f

    init {
        attrs?.let{
            val array : TypedArray = context.obtainStyledAttributes(it, R.styleable.DonutView)
            sprinkleCount = array.getInt(R.styleable.DonutView_num_sprinkles, 50)
            sprinkleHeight = array.getDimensionPixelSize(R.styleable.DonutView_sprinkle_height, 42).toFloat()
            sprinkleWidth = sprinkleHeight / 3
            array.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthAfterMargin = (measuredWidth - paddingStart - paddingEnd)
        centerX = widthAfterMargin / 2f

        val heightAfterMargin = (measuredHeight - paddingTop - paddingBottom)
        centerY = heightAfterMargin / 2f

        squareSize = widthAfterMargin.coerceAtMost(heightAfterMargin)

        holePath.apply {
            reset()
            addCircle(centerX, centerY, squareSize / 6f, Path.Direction.CW)
        }

        sprinkles = generateSprinkles(sprinkleCount, squareSize)

        val finalWidth = ViewGroup.resolveSize(squareSize, widthMeasureSpec)
        val finalHeight = ViewGroup.resolveSize(squareSize, heightMeasureSpec)
        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onDraw(canvas: Canvas?) {
        val saveCount = canvas?.save()
        if (Build.VERSION.SDK_INT >= 26) {
            canvas?.clipOutPath(holePath)
        } else {
            canvas?.clipPath(holePath, Region.Op.DIFFERENCE)
        }
        canvas?.drawCircle(centerX, centerY, width / 2f, basePaint)
        canvas?.drawCircle(centerX, centerY, width / 2.15f, icingPaint)

        sprinkles.forEach {
            val holeRadius = width / 6f

            val modDistance = holeRadius + sprinklePadding + it.radialDistance

            canvas?.save()
            canvas?.rotate(it.rotation, centerX, centerY) // rotate the entire canvas around the center
            canvas?.translate(0f, modDistance) // move the canvas to the sprinkle's position
            canvas?.rotate(it.rotation + 360f * it.angle, centerX, centerY) // rotate the canvas around the sprinkle's location

            sprinklePaint.color = it.color
            if (Build.VERSION.SDK_INT >= 21){
                canvas?.drawRoundRect(centerX - (sprinkleWidth / 2), centerY - (sprinkleHeight / 2), centerX + (sprinkleWidth / 2), centerY + (sprinkleHeight / 2), 10f, 10f, sprinklePaint)
            } else {
                canvas?.drawRect(centerX - (sprinkleWidth / 2), centerY - (sprinkleHeight / 2), centerX + (sprinkleWidth / 2), centerY + (sprinkleHeight / 2), sprinklePaint)
            }

            canvas?.restore()
        }

        canvas?.restoreToCount(saveCount!!)
    }

    private fun generateSprinkles(sprinkleCount: Int, size : Int): List<Sprinkle> {
        val random = Random()
        val list = mutableListOf<Sprinkle>()

        val outerRadius = size / 2f
        val innerRadius = size / 6f

        for (i in 0 until sprinkleCount) {
            list.add(
                Sprinkle(
                    color = sprinkleColors[i % sprinkleColors.size],
                    angle = random.nextFloat() * 360f,
                    radialDistance = random.nextFloat() * (outerRadius - innerRadius - 2 * sprinklePadding),
                    rotation = i * (360f / sprinkleCount)
                )
            )
        }

        return list
    }
}

private data class Sprinkle(
    val color: Int,
    val angle: Float,
    val radialDistance: Float,
    var rotation: Float
)