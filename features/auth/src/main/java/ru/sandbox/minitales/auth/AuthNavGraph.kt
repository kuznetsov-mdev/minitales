package ru.sandbox.minitales.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val authRoute = "auth"

sealed class AuthScreen(val route: String) {
    data object Splash : AuthScreen("$authRoute/splash")
    data object Login : AuthScreen("$authRoute/login")
    data object Signup : AuthScreen("$authRoute/signup")
}

fun NavGraphBuilder.authNavGraph(
    onAuthSuccess: () -> Unit,
    navController: NavController
) {
    navigation(startDestination = AuthScreen.Splash.route, route = authRoute) {

    }
}