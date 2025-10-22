package me.elias.unabshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    // Estado inicial de la ruta dependiendo de si hay usuario o no
    val startDestination = remember { mutableStateOf("login") }

    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startDestination.value = "home"
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination.value
    ) {
        // Pantalla de Login
        composable("login") {
            LoginScreen(
                onClickRegister = {
                    navController.navigate("register")
                },
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // Pantalla de Registro
        composable("register") {
            RegisterScreen(
                onClickBack = {
                    navController.popBackStack() // Regresar al login
                },
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Pantalla principal
        composable("home") {
            HomeScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}

