package ru.sandbox.minitales.auth.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.compose.MiniTalesTheme
import ru.sandbox.minitales.auth.AuthScreen
import ru.sandbox.minitales.auth.R
import ru.sandbox.minitales.theme.components.MiniTalesPreview

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    navController: NavController,
    onAuthSuccess: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    when (val state = uiState.value) {
        SplashUiState.Authenticated -> {
            LaunchedEffect(Unit) { //1*-see comment above
                onAuthSuccess() //calls only once.
            }
        }

        is SplashUiState.Splash -> {
            if (state.moveToLogin) {
                LaunchedEffect(Unit) {
                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(AuthScreen.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Splash()
            }
        }
    }
}

@Composable
fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.mini_tales),
            contentDescription = "Logo"
        )
    }
}

@Composable
@MiniTalesPreview
fun SplashPreview() {
    MiniTalesTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Splash()
        }
    }
}

/**
 * Putting onAuthSuccess inside Launched Effect(Unit)
 * ensures that it runs only once when the screen is first shown.
 * If we didn't use Launched Effect, the code could trigger an infinite
 * loop by calling onAuthSuccess in every recomposition,
 * leading to unexpected behavior. Launched Effect helps prevent this
 * by making sure the effect is tied to the composable life cycle and
 * xecutes only when the relevant key (in this case, Unit) changes,
 * avoiding repeated calls during recompositions.
 * **/