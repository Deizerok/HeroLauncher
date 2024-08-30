package core

import main_screen.homeScreen
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val versionsMinecraft = listOf("1.16.5", "1.15.4", "1.14.4", "1.13.3", "1.12.2")
    MaterialTheme {
        homeScreen(versionsMinecraft)
    }
}


@Composable
@Preview
fun defaultText(text: String, color: Color = Color.White) {
    Text(text, color = color)
}

fun main() = application {
    Window(
        state = WindowState(width = 1416.dp, height = 825.dp),
        icon = painterResource("icon-launcher.png"),
        title = "HeroLauncher",
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}