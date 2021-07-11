package com.canlioya.hellocompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canlioya.hellocompose.ui.theme.Cream

@Preview(showBackground = true)
@Composable
fun SampleItemLayout(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        RoundProfileImage()
        Spacer(Modifier.width(16.dp))
        Column {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Ali Conors", fontWeight = FontWeight.Bold, color = Color.Blue)
                Spacer(Modifier.width(16.dp))
                Text("3:50", fontStyle = FontStyle.Italic)
            }
            Text("Yeah I've been mainly refering to the JetNews sample")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpreadHorizontally(modifier: Modifier = Modifier) {
    val dayList = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        dayList.forEach {
            Text(text = it, fontWeight = FontWeight.Bold)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoxWithRoundedBackground(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .background(color = Cream, shape = RoundedCornerShape(10.dp))
    )
    {
        SampleItemLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun WeightedLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(modifier = modifier.weight(1f)
            .fillMaxWidth()
            .background(color = Color.Red)
        ) {
            Text(
                text = "First row",
                modifier = modifier.align(Alignment.Center).padding(8.dp),
            )
        }
        Row(modifier = Modifier.weight(2f)) {
            Box(modifier = modifier.weight(1f)
                .fillMaxHeight()
                .background(color = Color.Yellow)) {
                Text(text = "First column of second row",
                    modifier = modifier.align(Alignment.Center).padding(8.dp))
            }
            Box(modifier = modifier.weight(4f)
                .fillMaxHeight()
                .background(color = Color.Green)) {
                Text(text = "Second column of second row",
                    modifier = modifier.align(Alignment.Center).padding(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollableColumn() {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    val scrollState = rememberScrollState()

    Column(Modifier.height(100.dp).verticalScroll(scrollState)) {
        repeat(10) {
            Text("Item #$it")
        }
    }
}

