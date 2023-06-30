package com.example.culturecompass

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun UserAccountPage(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var animateState by remember { mutableStateOf(false) }
            val imageAlpha by animateFloatAsState(
                targetValue = if (animateState) 1f else 0f,
                animationSpec = tween(durationMillis = 1000)
            )
            val contentAlpha by animateFloatAsState(
                targetValue = if (animateState) 1f else 0f,
                animationSpec = tween(durationMillis = 1000, delayMillis = 500)
            )

            LaunchedEffect(key1 = true) {
                animateState = true
            }

            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .alpha(imageAlpha)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
                ,
                modifier = Modifier
                    .animateContentSize()
                    .alpha(contentAlpha)
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.padding(top = 15.dp))
                UserDetailsRow(
                    icon = Icons.Default.Person,
                    label = "Name",
                    value = "Ben Zeo"
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))


                UserDetailsRow(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = "9080685172"
                )
                Spacer(modifier = Modifier.padding(top = 15.dp))

                UserDetailsRow(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = "benzeo@gmail.com"
                )
                Spacer(modifier = Modifier.padding(top = 15.dp, start = 20.dp, end = 20.dp))
                AnimatedButton(
                    text = "Reset Password",
                    onClick = {
                        navController.navigate("Reset") {
                            popUpTo(navController.graph.startDestinationId) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    visible = contentAlpha == 1f
                )
                Spacer(modifier = Modifier.padding(top = 15.dp, start = 20.dp, end = 20.dp))
                AnimatedButton(
                    text = "LogOut",
                    onClick = {
                            navController.navigate("Login") {
                                popUpTo(navController.graph.startDestinationId) { inclusive = true }
                                launchSingleTop = true
                            }
                    },
                    visible = contentAlpha == 1f
                )
            }


        }
    }
}

@Composable
fun UserDetailsRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun AnimatedButton(
    text: String,
    onClick: () -> Unit,
    visible: Boolean
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 30.dp, end = 30.dp)
        ) {
            Text(text = text)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold2(navController: NavController) {
    Scaffold(topBar = { CustomTopBar2(navController) },
        bottomBar = { CustomBottomBar2(navController) },
        content = { pad -> MainContent2(navController, padding = pad) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar2(navController: NavController) {
    TopAppBar(

        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement =
                Arrangement.SpaceBetween, modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 85
                            .dp
                    )
            ) {
                val context = LocalContext.current
                var abc by remember { mutableStateOf(false) }

                IconButton(onClick = { abc = !abc }) {
                    Icon(
                        Icons.Filled.Menu, contentDescription = "", modifier = Modifier.size(30.dp)
                    )
                }
                DropdownMenu(
                    expanded = abc,
                    onDismissRequest = { abc = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("About") },
                        onClick = {
                            navController.navigate("About"){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        })
                    DropdownMenuItem(
                        text = { Text("FAQs") },
                        onClick = {
                            navController.navigate("Faq"){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Terms & Cons") },
                        onClick = {
                            navController.navigate("Tc"){
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
                Text(text = "Cultural Compass", fontWeight = FontWeight.ExtraBold, fontStyle = FontStyle.Italic, fontSize = 24.sp)

            }
        },
        modifier = Modifier.drawBehind {
            drawLine(
                Color.LightGray,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                5f
            )
        }
    )
}
@SuppressLint("ResourceType")
@Composable
fun CustomBottomBar2(navController: NavController) {
    remember { mutableStateOf(0) }
    BottomAppBar(
        modifier = Modifier
            .height(60.dp)
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 4f
                )
            } // Adjust the height as per your preference
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp), // Add padding to adjust spacing from the edges
            horizontalArrangement = Arrangement.SpaceBetween // Distribute space evenly
        ) {
            Icon(imageVector = Icons.Default.PlayArrow, "", modifier = Modifier.size(30.dp).clickable {

                navController.navigate("Mani") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true

                    navController.popBackStack()
                }
            })
            Icon(imageVector = Icons.Default.Home, "", modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("Welcome") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                })
            Icon(imageVector = Icons.Default.Person, "", modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.navigate("Profile") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                })        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContent2(navController: NavController, padding: PaddingValues) {
    Column(Modifier.padding(padding)) {
        UserAccountPage(navController)
    }
}