package com.canlioya.hellocompose.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canlioya.hellocompose.R

@Preview(showBackground = true)
@Composable
fun AnimateSize() {
    var large by remember {
        mutableStateOf(false)
    }

    val animateSize by animateDpAsState(targetValue = if (large) 200.dp else 300.dp)

    Box(modifier = Modifier
        .size(animateSize)
        .background(color = Color.Green)
        .clickable { large = !large }) {
        Text(
            "Click me!",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun AnimateColor() {
    var blue by remember {
        mutableStateOf(true)
    }

    val animateColor by animateColorAsState(if (blue) Color.Blue else Color.Yellow)

    Column {
        Button(onClick = { blue = !blue }) {
            Text("Change Color")
        }
        Spacer(Modifier.height(16.dp))
        Box(
            Modifier
                .size(128.dp)
                .background(animateColor)
        )
    }
}

private enum class BoxState {
    Small,
    Large
}

@Preview
@Composable
fun AnimateColorAndSize() {
    var boxState by remember {
        mutableStateOf(BoxState.Small)
    }

    val transition = updateTransition(targetState = boxState, label = "combinedAnim")

    val animatedColor by transition.animateColor(label = "colorAnimation") { state ->
        when (state) {
            BoxState.Small -> Color.Blue
            BoxState.Large -> Color.Yellow
        }
    }

    val animatedSize by transition.animateDp(label = "sizeAnimation") { state ->
        when (state) {
            BoxState.Small -> 64.dp
            BoxState.Large -> 128.dp
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            boxState = when (boxState) {
                BoxState.Small -> BoxState.Large
                BoxState.Large -> BoxState.Small
            }
        }) {
            Text("Change Color and Size")
        }
        Spacer(Modifier.height(16.dp))
        Box(
            Modifier
                .size(animatedSize)
                .background(animatedColor)
        )
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun AnimateVisibility() {
    var visible by remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier
        .width(200.dp)
        .height(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {visible = !visible} ){
            Text(if(visible) "Hide" else "Show")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible) {
            Box(modifier = Modifier
                .size(128.dp)
                .background(Color.Blue))
        }
    }
}

@Preview
@Composable
fun AnimateContentSize() {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { expanded = !expanded }) {
            Text(if(expanded) "Shrink" else "Expand")
        }
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier
            .background(Color.LightGray)
            .animateContentSize()) {
            Text(
                text = stringResource(R.string.long_text),
            fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp),
                maxLines = if(expanded) Int.MAX_VALUE else 2
            )
        }
    }
}

private enum class CrossState {
    TEXT,
    ICON
}

@Preview
@Composable
fun CrossFadeAnimation() {
    var scene by remember {
        mutableStateOf(CrossState.TEXT)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            scene = when(scene) {
                CrossState.TEXT -> CrossState.ICON
                CrossState.ICON -> CrossState.TEXT
            }
        }) {
            Text("TOGGLE")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Crossfade(targetState = scene) { scene ->
            when (scene) {
                CrossState.TEXT -> Text(text = "PHONE")
                CrossState.ICON -> Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone icon")
            }
        }

    }
}
