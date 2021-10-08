package com.canlioya.hellocompose.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canlioya.hellocompose.R

@Preview(showBackground = true)
@Composable
fun MyText() {
    Text(
        stringResource(R.string.hello_world),
        color = Color.Blue,
        fontSize = 30.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun MyEditText() {
    var textFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = textFieldState,
        label = {
            Text("Enter your name")
        },
        onValueChange = {
            textFieldState = it
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun SpannedString() {
    val text = buildAnnotatedString {
        withStyle(SpanStyle(color = Color.Red)) {
            append("H")
        }
        withStyle(SpanStyle(color = Color.Yellow)) {
            append("E")
        }
        withStyle(SpanStyle(color = Color.Green)) {
            append("L")
        }
        withStyle(SpanStyle(color = Color.Magenta)) {
            append("L")
        }
        withStyle(SpanStyle(color = Color.Blue)) {
            append("O")
        }
    }
    Text(
        text = text,
        modifier = Modifier.padding(8.dp)
    )
}
