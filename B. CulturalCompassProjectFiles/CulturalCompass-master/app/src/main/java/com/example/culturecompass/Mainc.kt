package com.example.culturecompass

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


data class Item(val imageId: Int, val title: String, val description: String)


val itemList = listOf(
    Item(R.drawable.img, "INDIA", description1),
    Item(R.drawable.img_2, "ISFAHAN", description2),
    Item(R.drawable.img_3, "ALEXANDRIA", description3),
    Item(R.drawable.img_4, "SAMARKAND ", description4),
    Item(R.drawable.img_5, "CUSCO, PERU", description5),
    Item(R.drawable.img_6, "ATHENS, GREECE", description6),
    Item(R.drawable.img_7, "PRAGUE", description7),
    Item(R.drawable.img_8, "BOSTON, USA", description8),
    Item(R.drawable.img_9, "BEIJING, CHINA", description9),
    Item(R.drawable.img_10, "BERLIN,GERMANYAthens", description10),
    Item(R.drawable.img_11, "ISTANBUL,TURKEY", description11),
    Item(R.drawable.img_12, "CARTHAGE, TUNISIA", description12),
    Item(R.drawable.img_13, "VARANASI, INDIA", description13),
    Item(R.drawable.img_14, "FLORENCE", description14),
    Item(R.drawable.img_15, "VIENNA", description15),
    Item(R.drawable.img_21, "JERUSALEM", description16),
    Item(R.drawable.img_16, "GRANADA", description17),
    Item(R.drawable.img_17, "ROME", description18),
    Item(R.drawable.img_18, "LONDON", description19),
    Item(R.drawable.img_20, "SHANGAI", description20),
    )


@Composable
fun LazyColumnExample() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "main") {
        composable("main") {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(itemList) { item ->
                    ItemColumn(item = item) {
                        navController.navigate("detail/${item.imageId}/${item.title}/${item.description}")
                    }
                }
            }
        }
        composable(
            "detail/{imageId}/{title}/{description}",
            arguments = listOf(navArgument("imageId") {}, navArgument("title") {}, navArgument("description") {})
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")?.toIntOrNull()

            val title = backStackEntry.arguments?.getString("title")

            val description = backStackEntry.arguments?.getString("description")


            if (imageId != null && description != null) {
                val item = itemList.find { it.imageId == imageId && it.title == title && it.description == description }
                if (item != null) {
                    ItemDetail(item = item)
                } else {
                    // Handle invalid item
                    Text("Invalid item")
                }
            } else {
                // Handle invalid arguments
                Text("Invalid arguments")
            }
        }
    }
}

@Composable
fun ItemColumn(item: Item, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onItemClick)
    ) {
        Image(
            painter = painterResource(item.imageId),
            contentDescription = "Image",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tap to view more..",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
@Composable
fun ItemDetail(item: Item) {
    var a by remember {
        mutableStateOf(false)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(item.imageId),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = item.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold1(navController: NavController) {
    Scaffold(topBar = { CustomTopBar1(navController) },
        bottomBar = { CustomBottomBar1(navController) },
        content = { pad -> MainContent1(pad) }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar1(navController: NavController) {
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

@SuppressLint("SuspiciousIndentation")
@Composable
fun CustomBottomBar1(navController: NavController) {
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
                    navController.navigate("Mani"){
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true

                        navController.popBackStack()
            }
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


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainContent1(padding: PaddingValues) {
    Column(Modifier.padding(padding)) {
        LazyColumnExample()
    }
}
