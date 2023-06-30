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


val textt = "\nTerms and Conditions:\n" +
"\n" +
"\nPlease read these Terms and Conditions (\"Terms\") carefully before using the Cultural Compass mobile application (\"the App\"). These Terms govern your use of the App, so it is important that you understand them. By accessing or using the App, you agree to be bound by these Terms. If you do not agree with any part of these Terms, you may not use the App.\n" +
"\n" +
"\n\nContent and Use of the App:\n" +
"a. The content provided in the App, including city profiles, recommendations, itineraries, maps, and other information, is for informational purposes only. While we strive to provide accurate and up-to-date content, we make no representations or warranties of any kind, express or implied, regarding the accuracy, reliability, or completeness of the information.\n" +
"b. You may use the App solely for personal, non-commercial purposes. You may not modify, distribute, transmit, display, perform, reproduce, publish, license, create derivative works from, transfer, or sell any information, software, products, or services obtained from the App.\n" +
"\n" +
"\n\nUser Accounts:\n" +
"a. In order to access certain features of the App, you may be required to create a user account. You are responsible for maintaining the confidentiality of your account information and for all activities that occur under your account.\n" +
"b. You agree to provide accurate and complete information when creating your account. You must promptly update your account information if there are any changes.\n" +
"c. You are solely responsible for the security and use of your account. If you become aware of any unauthorized use of your account, you must notify us immediately.\n" +
"\n" +
"\n\nIntellectual Property:\n" +
"a. The App and its content, including but not limited to text, graphics, logos, images, software, and any other materials, are the property of Cultural Compass or its licensors and are protected by intellectual property laws.\n" +
"b. You may not use, copy, reproduce, modify, distribute, transmit, display, perform, publish, license, create derivative works from, or sell any intellectual property contained in the App without our prior written permission.\n" +
"\n" +
"\n\nThird-Party Links and Services:\n" +
"a. The App may contain links to third-party websites, services, or resources that are not owned or controlled by Cultural Compass. We are not responsible for the content, privacy policies, or practices of any third-party websites or services.\n" +
"b. Your interactions with third-party websites or services are solely between you and the third party. We recommend reviewing the terms and conditions and privacy policies of any third-party websites or services that you access.\n" +
"\n" +
"\n\nLimitation of Liability:\n" +
"a. To the extent permitted by applicable law, Cultural Compass and its affiliates, officers, directors, employees, agents, and licensors shall not be liable for any indirect, incidental, special, consequential, or punitive damages arising out of or in connection with your use of the App.\n" +
"b. We do not guarantee the availability, accuracy, or reliability of the App. The App is provided on an \"as is\" and \"as available\" basis, without any warranties of any kind, express or implied.\n" +
"\n" +
"\n\nModifications and Termination:\n" +
"a. We reserve the right to modify or terminate the App, these Terms, or any feature of the App at any time without prior notice.\n" +
"b. We may also suspend or terminate your access to the App if we believe you have violated these Terms or engaged in any unauthorized or unlawful activities.\n" +
"\n" +
"\n\nGoverning Law:\n" +
"a. These Terms shall be governed by and construed in accordance with the laws of [Jurisdiction], without regard to its conflict of laws principles.\n" +
"b. Any legal action or proceeding arising out of or relating to these Terms or the use of the App shall be exclusively brought in the courts located in"


val paragraphst = textt.split("\n\n")


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffoldt(navController: NavController) {
    Scaffold(topBar = { CustomTopBart(navController) },
        bottomBar = { CustomBottomBart(navController) },
        content = { pad -> MainContentt(navController, padding = pad) }
    )
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContentt(navController: NavController, padding: PaddingValues) {

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(true) {
        visible = true
    }

    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Terms & Conds",
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 60.dp)
        )

        LazyColumn {
            items(paragraphst) { paragrapht ->
                Text(text = paragrapht)
            }
        }
    }

}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBart(navController: NavController) {
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
fun CustomBottomBart(navController: NavController) {
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


