package com.canlioya.hellocompose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canlioya.hellocompose.R
import com.canlioya.hellocompose.ui.theme.Cream

@Preview(showBackground = true)
@Composable
fun ImageWithFrame(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.header),
        contentDescription = null,
        modifier = modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            .background(color = Cream, shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun RoundProfileImage(modifier: Modifier = Modifier) {
    Image(
        painterResource(id = R.drawable.ali),
        contentDescription = null,
        modifier = modifier
            .padding(4.dp)
            .border(2.dp, Color.Red, CircleShape)
            .padding(4.dp)
            .clip(CircleShape)
            .size(60.dp)
    )
}

@Preview
@Composable
fun DynamicShape() {
    var shape by remember { mutableStateOf<Shape>(CircleShape) }

    Image(
        painterResource(id = R.drawable.header),
        contentDescription = " ",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(256.dp)
            .padding(16.dp)
            .clip(shape)
            .border(6.dp, MaterialTheme.colors.primary, shape)
            .border(12.dp, MaterialTheme.colors.secondary, shape)
            .border(18.dp, MaterialTheme.colors.background, shape)
            .clickable {
                shape =
                    if (shape == CircleShape) CutCornerShape(topStart = 32.dp, bottomEnd = 32.dp)
                    else CircleShape
            })
}