package com.canlioya.hellocompose.ui

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.canlioya.hellocompose.R
import kotlin.math.PI
import kotlin.math.roundToInt

@Preview
@Composable
fun SimpleLine() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 5F
        )
    }
}

@Preview
@Composable
fun TwoLineDivider() {
    Column(modifier = Modifier.fillMaxWidth()) {
        SimpleHorizontalLine()
        Spacer(modifier = Modifier.height(5.dp))
        SimpleHorizontalLine()
    }
}

@Composable
fun SimpleHorizontalLine(
    modifier: Modifier = Modifier,
    startY: Float = 0f,
    strokeWidth: Float = 5f,
    color: Color = Color.Red
) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        val canvasWidth = size.width

        drawLine(
            start = Offset(x = 0f, startY),
            end = Offset(x = canvasWidth, startY),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

@Composable
fun SimpleVerticalLine(
    modifier: Modifier = Modifier,
    startX: Float = 0f,
    strokeWidth: Float = 3f,
    color: Color = Color.Red
) {
    Canvas(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        val canvasHeight = size.height

        drawLine(
            start = Offset(x = startX, y = 0f),
            end = Offset(x = startX, y = canvasHeight),
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

@Preview
@Composable
fun SimpleCircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )
    }
}

@Preview
@Composable
fun SimpleRect() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        withTransform({
            translate(left = canvasWidth / 5F)
            rotate(degrees = 45F)
        }) {
            drawRect(
                color = Color.Gray,
                topLeft = Offset(x = canvasWidth / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}
