package com.canlioya.hellocompose.ui

import android.widget.SimpleExpandableListAdapter
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleList() {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(100) {
            Text("Item #$it")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListWithPositions() {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        itemsIndexed(listOf("This", "is", "jetpack","compose", "try", "it", "today")) { index , text ->
            Row {
                Text("$index.")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = text)
            }
        }
    }
}