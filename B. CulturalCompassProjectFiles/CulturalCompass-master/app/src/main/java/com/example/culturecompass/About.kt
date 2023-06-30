package com.example.culturecompass

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val text = "\nWelcome to Cultural Compass, the ultimate guide to exploring culturally rich cities around the world! Whether you're a seasoned traveler or a curious explorer, this app is your key to unlocking the hidden gems and vibrant cultures that cities have to offer.\n" +
        "\n" +
        "\nAbout the App:\n" +
        "\n" +
        "\nCultural Compass is your digital companion, designed to provide you with a comprehensive and immersive experience of the world's most culturally significant cities. Our mission is to connect you with the heart and soul of each destination, allowing you to delve deep into the unique traditions, history, art, cuisine, and vibrant communities that make these cities truly remarkable.\n" +
        "\n" +
        "\nFeatures:\n" +
        "\n" +
        "\nCity Profiles: Discover an extensive collection of meticulously curated city profiles that showcase the rich cultural tapestry of each location. From iconic landmarks to off-the-beaten-path neighborhoods, we provide you with a wealth of information, giving you a complete understanding of the city's heritage, traditions, and artistic expressions.\n" +
        "\n" +
        "\nCultural Highlights: Unearth the most fascinating cultural highlights that define each city. Whether it's ancient temples, awe-inspiring museums, bustling marketplaces, or lively festivals, we bring you a comprehensive list of must-visit attractions and experiences that will leave you captivated.\n" +
        "\n" +
        "\nLocal Insights: Get an insider's perspective with our local insights feature. We collaborate with passionate locals who provide authentic and personal recommendations, ensuring you uncover hidden gems that are not found in guidebooks. Discover the best spots for traditional cuisine, secret art galleries, or lesser-known cultural events that will truly enrich your journey.\n" +
        "\n" +
        "\nEvents and Festivals: Stay up to date with the vibrant cultural calendar of each city. Cultural Compass keeps you informed about upcoming events, festivals, and celebrations, ensuring you never miss out on the opportunity to immerse yourself in the local traditions and join in the festivities.\n" +
        "\n" +
        "\nItineraries: Whether you have a few days or a week to explore, we offer carefully crafted itineraries to help you make the most of your time in each city. Our itineraries cater to different interests and provide a balanced mix of must-see landmarks, cultural experiences, and hidden gems, ensuring an unforgettable adventure tailored to your preferences.\n" +
        "\n" +
        "\nInteractive Maps:Seamlessly navigate your way through the city with our interactive maps. Locate attractions, landmarks, dining options, and cultural hotspots with ease, and use our integrated navigation feature to guide you to your desired destinations effortlessly.\n" +
        "\n" +
        "\nPersonalized Recommendations: With Cultural Compass, we understand that every traveler is unique. By considering your interests, preferences, and previous explorations, our app offers personalized recommendations tailored to your individual tastes, helping you create a truly personalized and enriching travel experience.\n"


val paragraphs = text.split("\n\n")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffoldA(navController: NavController) {
    Scaffold(topBar = { CustomTopBarA(navController) },
        bottomBar = { CustomBottomBarA(navController) },
        content = { pad -> MainContentA(navController, padding = pad) }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContentA(navController: NavController, padding: PaddingValues) {

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        visible = true
    }

    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "About",
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 60.dp)
        )

        LazyColumn {
            items(paragraphs) { paragraph ->
                Text(text = paragraph)
            }
        }
    }

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBarA(navController: NavController) {
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
fun CustomBottomBarA(navController: NavController) {
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
            Icon(imageVector = Icons.Default.PlayArrow, "", modifier = Modifier
                .size(30.dp)
                .clickable {
                    navController.popBackStack()
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
                })
        }
    }
}