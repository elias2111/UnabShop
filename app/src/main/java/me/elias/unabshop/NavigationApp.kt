package me.elias.unabshop

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationApp(){
    val  myNavController = rememberNavController()
    val myStartDestination: String = "login"
    NavHost(
        navController = myNavController,
        startDestination = myStartDestination
    ) {
        composable("login") {
            LoginScreen(onClickRegister ={
                myNavController.navigate("register")

            })
        }
        composable("register") {
            RegisterScreen(onClickBack = {
                myNavController.navigate("Login")
            })
        }
        composable (route = "Home"){
            HomeScreen()
        }
    }
}
