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


val textf = "Here are some frequently asked questions (FAQs) about Cultural Compass along with their answers:\n" +
        "\n\n" +
        "\nQ: How can Cultural Compass help me in planning my trip?\n" +
        "\nA: Cultural Compass provides comprehensive city profiles, cultural highlights, local insights, and itineraries to help you plan your trip. You can explore the rich heritage, traditions, art, cuisine, and community of each city, ensuring a well-rounded and culturally immersive experience.\n" +
        "\n" +
        "\n\nQ: Can I trust the information provided in Cultural Compass?\n" +
        "\nA: Absolutely! We take great care to curate accurate and up-to-date information from reliable sources and collaborate with local experts to ensure the authenticity of our content. However, we always recommend cross-referencing information and checking official sources for the most current details.\n" +
        "\n" +
        "\n\nQ: Is Cultural Compass available offline?\n" +
        "\nA: Yes! We understand that connectivity may be limited while traveling, so we offer an offline mode for essential information such as city profiles, maps, and itineraries. Make sure to download the desired content in advance so you can access it without an internet connection.\n" +
        "\n" +
        "\n\nQ: How often is the content in Cultural Compass updated?\n" +
        "\nA: We strive to keep our content as current as possible. We regularly update the app with new attractions, events, and recommendations. However, it's always a good idea to check for updates before your trip to ensure you have the latest information.\n" +
        "\n" +
        "\n\nQ: Can I contribute to Cultural Compass?\n" +
        "\nA: We appreciate your enthusiasm! At the moment, we don't have a user-generated content feature, but we value feedback and suggestions. You can reach out to us through the app's support channel to share your thoughts and ideas.\n" +
        "\n" +
        "\n\nQ: Is Cultural Compass available for both iOS and Android devices?\n" +
        "\nA: N! Cultural Compass is only available for Android devices. You can download it from the Google Play Store.\n" +
        "\n" +
        "\n\nQ: How can I contact customer support if I encounter any issues with the app?\n" +
        "\nA: If you have any questions, concerns, or technical issues, you can reach our customer support team by visiting the \"Support\" section within the app. Our dedicated team will be happy to assist you and provide prompt solutions to ensure a seamless experience.\n" +
        "\n" +
        "\n\nQ: Can I sync my Cultural Compass account across multiple devices?\n" +
        "\nA: Yes, you can! Cultural Compass supports account synchronization, allowing you to access your saved preferences, itineraries, and personalized recommendations across multiple devices. Simply sign in to your account on each device to stay connected.\n"

val paragraphsf = textf.split("\n\n")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffoldf(navController: NavController) {
    Scaffold(topBar = { CustomTopBarf(navController) },
        bottomBar = { CustomBottomBarf(navController) },
        content = { pad -> MainContentf(navController, padding = pad) }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContentf(navController: NavController, padding: PaddingValues) {

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        visible = true
    }

    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "FAQ's",
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 60.dp)
        )

        LazyColumn {
            items(paragraphsf) { paragraphf ->
                Text(text = paragraphf)
            }
        }
    }

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBarf(navController: NavController) {
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
fun CustomBottomBarf(navController: NavController) {
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

