package main_screen

import values.ColorHmm
import values.ColorNicknameMenu
import values.ColorPlay
import values.ColorPrimaryColorApp
import values.ColorVersions
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import core.defaultText
import kotlinx.coroutines.launch

@Composable
fun labelRow() {
    Row(
        modifier = Modifier.fillMaxWidth().background(ColorPrimaryColorApp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(24.dp, 6.dp),
            painter = painterResource("icon-launcher.png"),
            contentDescription = "IconLauncher"
        )
        defaultText("HeroLauncher")
        Spacer(Modifier.weight(1f))
        Text("Main Menu", modifier = Modifier.padding(24.dp, 6.dp), color = Color.White)

    }
}

@Composable
fun versionsAndPicture(versionsMinecraft: List<String>, onVersionClick: (String) -> Unit) {
    Row() {
        Column(
            modifier = Modifier.background(ColorPrimaryColorApp),
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.9f)
            ) {

                items(items = versionsMinecraft) { version ->
                    Button(colors = ButtonDefaults.buttonColors(ColorVersions),
                        modifier = Modifier.padding(20.dp, 4.dp).height(75.dp).width(271.dp),
                        onClick = { onVersionClick(version) }) {
                        defaultText("Minecraft $version")
                    }
                }
            }
        }
        Column(

        ) {
            Image(
                modifier = Modifier.fillMaxHeight(0.9f),
                painter = painterResource("picture-java.png"), contentDescription = "IconLauncher"
            )
        }

    }
}

@Preview
@Composable
fun homeScreen(
    versionsMinecraft: List<String>
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
        labelRow()
        versionsAndPicture(versionsMinecraft = versionsMinecraft) { selected = it }

        Row(
            modifier = Modifier.height(100.dp).fillMaxWidth().align(Alignment.End)
                .background(ColorPrimaryColorApp),  // Закріплюємо Row в кінці екрану
            horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically
        ) {
            Button(colors = ButtonDefaults.buttonColors(ColorPlay),
                modifier = Modifier.padding(20.dp, 4.dp).height(75.dp).width(271.dp),
                onClick = {
                    if (selected.isEmpty()) {
                        coroutineScope.launch {
                            snackBarHostState.showSnackbar("No version selected!")
                        }
                    } else {
                        expanded = !expanded
                    }
                }) {
                defaultText(if (expanded) "Close" else "Play")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                selected.ifEmpty { "Not Selected Version" }, color = Color.White, modifier = Modifier.padding(12.dp)
            )
            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.padding(10.dp).clip(shape = RoundedCornerShape(6.dp)).background(ColorNicknameMenu)
                    .width(280.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource("icon-launcher.png"),
                    contentDescription = "IconLauncher"
                )
                Column {
                    defaultText("Username")
                    defaultText(
                        "Local Account", ColorHmm
                    )
                }
                Spacer(Modifier.weight(1f))

                IconButton(
                    onClick = { },
                ) {
                    Icon(
                        painterResource("settings-icon.svg"), "settings", tint = Color.White
                    )
                }
            }
            SnackbarHost(hostState = snackBarHostState)
        }

    }
}
