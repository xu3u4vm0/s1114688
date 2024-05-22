package com.example.s1114688

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.s1114688.ui.theme.S1114688Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S1114688Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Main()

                }
            }
        }
    }
}
@Composable
fun FirstScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("瑪利亞基金會服務總覽")}
    Column(modifier = Modifier
        .fillMaxSize()
    )
    {
        Text(text = title, color = Color.Blue)
        var appear by remember { mutableStateOf(true) }
        Column {
            AnimatedVisibility(
                visible = appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )

            ) {
                Image(
                    painter = painterResource(id = R.drawable.service),
                    contentDescription = "服務總覽",
                )
            }

            AnimatedVisibility(
                visible = !appear,
                enter = fadeIn(
                    initialAlpha = 0.1f,
                    animationSpec = tween(durationMillis = 1500)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 1500)
                )

            ) {
                Image(
                    painter = painterResource(id = R.drawable.xuan),
                    contentDescription = "xuan",
                )
            }
            Button(
                onClick = { appear = !appear }
            ) {
                if (appear) {
                    Text(text = "資管二A李易軒")
                    title = "瑪利亞基金會服務總覽"
                } else {
                    Text(text = "服務總覽")
                    title = "關於APP作者"
                }
            }

        }
    }
}

@Composable
fun SecondScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
    ){
        Text(text = "主要機構", color = Color.Red)


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    var showMenu by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = { Image(painter = painterResource(id = R.drawable.maria), contentDescription ="maria" ) },
            actions = {
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")

                }
                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpFirst")})

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpSecond")})
                }

            }
        )

        NavHost(navController = navController, startDestination = "JumpFirst") {
            composable("JumpFirst") {
                FirstScreen(navController = navController)
            }
            composable("JumpSecond") {
                SecondScreen(navController = navController)
            }
        }
    }
}