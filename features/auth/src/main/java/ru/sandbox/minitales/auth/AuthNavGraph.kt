package ru.sandbox.minitales.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.sandbox.minitales.auth.login.LoginScreen
import ru.sandbox.minitales.auth.splash.SplashScreen

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
        composable(AuthScreen.Splash.route) {
            SplashScreen(
                viewModel = hiltViewModel(),
                navController = navController,
                onAuthSuccess = onAuthSuccess
            )
            navController.navigate(AuthScreen.Login.route)
        }

        composable(AuthScreen.Login.route) {
            LoginScreen(hiltViewModel())
        }
    }
}