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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun GradientButton4(
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
                navController.navigate("Register"){
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

@Composable
fun GradientButton5(
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
            navController.navigate("Login"){
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


@Composable
fun WelcomeScreen1(navController: NavController) {

    var visible by remember { mutableStateOf(false) }
    //val navController = Navigator.current

    LaunchedEffect(true) {
        visible = true
    }

    WelcomeScreenContent1(visible = visible, navController = navController) {
        /*  navController.navigate(Destination.home) {
              popUpTo(Destination.welcome) { inclusive = true }
          }*/
    }
}

@Composable
fun WelcomeScreenContent1(
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

            AnimatedTitle1(visible = visible)

            AnimatedImage1(visible = visible)

            AnimatedButton1(visible = visible, onClick = onGetStarted, navController = navController)

            Spacer(modifier = Modifier.padding(10.dp))

            AnimatedButton2(visible = visible, onClick = onGetStarted, navController = navController)

        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedImage1(visible: Boolean) {
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
fun AnimatedText1(
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
fun AnimatedTitle1(visible: Boolean) {
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


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedButton1(visible: Boolean, onClick: () -> Unit, navController: NavController) {
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
        GradientButton4(
            gradientColors = gradientColor,
            cornerRadius = cornerRadius,
            nameButton = "Sign Up",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
            navController = navController
        )
    }
}




@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedButton2(visible: Boolean, onClick: () -> Unit, navController: NavController) {

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
        GradientButton5(
            gradientColors = gradientColor,
            cornerRadius = cornerRadius,
            nameButton = "Sign In",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp),
            navController = navController
        )
    }
}

