package ru.sandbox.minitales

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ru.sandbox.minitales.auth.authNavGraph
import ru.sandbox.minitales.auth.authRoute

@Composable
fun MiniTalesNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = authRoute
    ) {
        authNavGraph(
            navController = navHostController,
            onAuthSuccess = {

            }
        )
    }
}