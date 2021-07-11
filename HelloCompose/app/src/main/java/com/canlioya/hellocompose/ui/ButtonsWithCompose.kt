package com.canlioya.hellocompose.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canlioya.hellocompose.R

@Preview(showBackground = true)
@Composable
fun ClickCounterButton() {

    val count = remember { mutableStateOf(0) }

    Button(onClick = { count.value++ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count.value > 5) Color.Green else Color.Yellow
        )) {
        Text("I've been clicked ${count.value} times")
    }
}

@Preview(showBackground = true)
@Composable
fun LikeButtonWithIcon() {
    val isLiked = remember { mutableStateOf(false) }

    Button(onClick = {isLiked.value = !isLiked.value}) {
        Image(painterResource(id = if(isLiked.value) R.drawable.ic_favorite else R.drawable.ic_not_favorite), null,
            modifier = Modifier.size(width = 20.dp, height = 20.dp))
        Spacer(Modifier.width(8.dp))
        Text(text = "Like")
    }
}

@Composable
fun FavoriteActionButton() {
    var isFavorite by remember { mutableStateOf(false) }

    IconButton(onClick = { isFavorite = !isFavorite }) {
        Icon(imageVector = if(isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
            contentDescription = null)
    }
}