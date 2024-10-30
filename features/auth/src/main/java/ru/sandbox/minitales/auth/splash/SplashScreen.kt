package ru.sandbox.minitales.auth.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.compose.MiniTalesTheme
import ru.sandbox.minitales.auth.R
import ru.sandbox.minitales.theme.components.MiniTalesPreview

@Composable
fun SplashScreen() {
    Splash()
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