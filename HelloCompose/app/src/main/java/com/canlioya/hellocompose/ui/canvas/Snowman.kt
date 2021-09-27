package com.canlioya.hellocompose.ui.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import com.canlioya.hellocompose.ui.theme.Carotte
import com.canlioya.hellocompose.ui.theme.DarkBrown
import com.canlioya.hellocompose.ui.theme.LightBrown

@Composable
fun EmotionalFace() {

    val mouthPath by remember {
        mutableStateOf(Path())
    }

    val nosePath by remember {
        mutableStateOf(Path())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Blue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .weight(1f),
            onDraw = {
                // Head
                drawOval(
                    color = Color.White,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )

                val eyesWidth = size.width * 0.2f
                val eyesHeight = size.height * 0.2f

                // Left eye
                drawOval(
                    color = Color.Black,
                    topLeft = Offset(size.width * 0.20f, size.height / 4),
                    size = Size(eyesWidth, eyesHeight)
                )

                // Right eye
                drawOval(
                    color = Color.Black,
                    topLeft = Offset((size.width * 0.60f), size.height / 4),
                    size = Size(eyesWidth, eyesHeight)
                )

                // Draw a smiling mouth
                mouthPath.reset()
                mouthPath.moveTo(size.width * 0.22f, size.height * 0.6f)
                mouthPath.quadraticBezierTo(
                    size.width * 0.5f,
                    size.height * 0.70f,
                    size.width * 0.78f,
                    size.height * 0.6f
                )
                mouthPath.quadraticBezierTo(
                    size.width * 0.5f,
                    size.height * 0.95f,
                    size.width * 0.22f,
                    size.height * 0.6f
                )

                drawPath(path = mouthPath, color = Color.Red)

                // Draw nose
                nosePath.reset()
                nosePath.moveTo(size.width * 0.5f, size.height * 0.4f)
                nosePath.lineTo(size.width * 0.5f, size.height * 0.54f)
                nosePath.lineTo(size.width * 0.9f, size.height * 0.47f)
                nosePath.close()

                drawPath(nosePath, color = Carotte)
            }
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            onDraw = {
                // Draw body
                drawOval(
                    color = Color.White,
                    topLeft = Offset(0f, 0f),
                    size = Size(size.width, size.height)
                )

                // Draw left arm
                drawLine(
                    Brush.linearGradient(
                        colors = listOf(LightBrown, DarkBrown)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.2f, size.height * 0.2f),
                    strokeWidth = 30.dp.toPx()
                )

                // Draw right arm
                drawLine(
                    Brush.linearGradient(
                        colors = listOf(DarkBrown, LightBrown)
                    ),
                    start = Offset(size.width, 0f),
                    end = Offset(size.width * 0.8f, size.height * 0.2f),
                    strokeWidth = 30.dp.toPx()
                )

                // Draw buttons
                drawButton(size.width / 2f, size.height * 0.2f)
                drawButton(size.width / 2f, size.height * 0.4f)
                drawButton(size.width / 2f, size.height * 0.6f)
                drawButton(size.width / 2f, size.height * 0.8f)
            }
        )
    }
}

private fun DrawScope.drawButton(centerX: Float, centerY: Float) {
    drawCircle(
        color = Color.Black,
        center = Offset(centerX, centerY),
        radius = 40f
    )
}
