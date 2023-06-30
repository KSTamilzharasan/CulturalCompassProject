package com.example.culturecompass

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun WelcomeScreen(navController: NavController) {

    var visible by remember { mutableStateOf(false) }
    //val navController = Navigator.current

    LaunchedEffect(true) {
        visible = true
    }


    CustomScaffold(navController)

//    WelcomeScreenContent(visible = visible, navController = navController) {
//        /*  navController.navigate(Destination.home) {
//              popUpTo(Destination.welcome) { inclusive = true }
//          }*/
//    }
}


@Composable
fun WelcomeScreenContent(
    visible: Boolean, navController: NavController,
    onGetStarted: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.bg),
                contentScale = ContentScale.FillBounds
            )) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            AnimatedTitle(visible = visible)

            AnimatedImage(visible = visible)

            AnimatedButton(visible = visible, onClick = onGetStarted, navController = navController)


        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(navController: NavController) {
    Scaffold(topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        content = { pad -> MainContent(navController, padding = pad) }
    )
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContent(navController: NavController, padding: PaddingValues) {

    var visible by remember { mutableStateOf(false) }
         LaunchedEffect(true) {
            visible = true
        }

        WelcomeScreenContent(visible = visible, navController = navController) {
        }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavController) {
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

@Composable
fun CustomBottomBar(navController: NavController) {
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
            Icon(imageVector = Icons.Default.PlayArrow, "", modifier = Modifier.size(30.dp).clickable{
                navController.popBackStack()
            })
            Icon(imageVector = Icons.Default.Home, "", modifier = Modifier.size(30.dp).clickable{
                navController.navigate("Welcome") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            })
            Icon(imageVector = Icons.Default.Person, "", modifier = Modifier.size(30.dp).clickable {
                navController.navigate("Profile") {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            })
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedImage(visible: Boolean) {
    val imageEnterTransition = fadeIn(
        animationSpec = tween(2000)
    )

    AnimatedVisibility(
        visible = visible,
        enter = imageEnterTransition,
    ) {
        Image(
            painter = painterResource(R.drawable.com),
            contentDescription = "",
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
        )
    }
}

@Composable
fun AnimatedText(
    text: String,
    textStyle: TextStyle,
    colorAnimationSpec: AnimationSpec<Color>,
    sizeAnimationSpec: AnimationSpec<Float>,
    modifier: Modifier = Modifier
) {
    val animatedColor by animateColorAsState(
        targetValue = Color.Red, // Replace with your desired color
        animationSpec = colorAnimationSpec
    )

    val fontSize by animateFloatAsState(
        targetValue = 40f, // Replace with your desired font size
        animationSpec = sizeAnimationSpec
    )

    Text(
        text = text,
        style = textStyle.copy(
            color = animatedColor,
            fontSize = fontSize.sp
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedButton(visible: Boolean, onClick: () -> Unit, navController: NavController) {
    val buttonEnterTransition = fadeIn(
        animationSpec = tween(1000, 2600)
    ) + slideInVertically(
        initialOffsetY = { 100 },
        animationSpec = tween(1000, 2600)
    )
    AnimatedVisibility(
        visible = visible,
        enter = buttonEnterTransition,
    ) {
        val gradientColor = listOf(Color(0xFF484BF1), Color(0xFF673AB7))
        val cornerRadius = 16.dp
        GradientButton3(
            gradientColors = gradientColor,
            cornerRadius = cornerRadius,
            nameButton = "Explore",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
            navController = navController
        )
    }
}




@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedTitle(visible: Boolean) {
    val titleEnterTransition = fadeIn(
        animationSpec = tween(1000, 1600)
    ) + slideInVertically(
        initialOffsetY = { -100 },
        animationSpec = tween(1000, 1600)
    )

    AnimatedVisibility(
        visible = visible,
        enter = titleEnterTransition,
    ) {
        AnimatedText(
            text = stringResource(R.string.welcome_title),
            textStyle = MaterialTheme.typography.titleLarge,
            colorAnimationSpec = tween(durationMillis = 1000),
            sizeAnimationSpec = tween(durationMillis = 1000)
        )
    }
}


@Composable
fun GradientButton3(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    navController: NavController
) {

    val cont = LocalContext.current
    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = {
            navController.navigate("Mani"){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        },
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.Text(
                text = nameButton,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

