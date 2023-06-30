package com.example.culturecompass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.culturecompass.ui.theme.CultureCompassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CultureCompassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Home()
                }
            }
        }
    }
}


@Composable
fun Home(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Land", builder = {
        composable("Land", content = { WelcomeScreen1(navController = navController)})
        composable("Login", content = {LoginPage(navController = navController)})
        composable("Register", content = { RegisterPage(navController = navController) })
        composable("Welcome", content = { WelcomeScreen(navController = navController) })
        composable("Mani", content = { CustomScaffold1(navController = navController) })
        composable("Profile", content = { CustomScaffold2(navController = navController) })
        composable("Reset", content = { ResetPage(navController = navController) })
        composable("About", content = { CustomScaffoldA(navController = navController) })
        composable("Faq", content = { CustomScaffoldf(navController = navController) })
        composable("Tc", content = { CustomScaffoldt(navController = navController) })
    } )
}
