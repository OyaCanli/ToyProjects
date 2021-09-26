package com.canlioya.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.canlioya.hellocompose.ui.AnimateColorAndSize
import com.canlioya.hellocompose.ui.FavoriteActionButton
import com.canlioya.hellocompose.ui.canvas.MusicBox
import com.canlioya.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MusicBox()
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
                        FavoriteActionButton()
                    }
                )
            }
        ) { // A surface container using the 'background' color from the theme
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier.padding(16.dp)
            ) {
                content()
            }
        }
    }
}
