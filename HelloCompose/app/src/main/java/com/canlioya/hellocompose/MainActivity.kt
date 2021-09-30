package com.canlioya.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.canlioya.hellocompose.ui.AnimateColorAndSize
import com.canlioya.hellocompose.ui.canvas.SnowMan
import com.canlioya.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                SnowMan()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        AnimateColorAndSize()
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    HelloComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Hello Compose")
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }
                    }
                )
            }
        ) { // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colors.background
            ) {
                content()
            }
        }
    }
}
