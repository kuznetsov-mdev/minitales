package ru.sandbox.minitales.theme.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.MiniTalesTheme

@Composable
fun Appbar(
    modifier: Modifier = Modifier,
    title: String,
    navIcon: ImageVector? = null,
    onNav: () -> Unit =  {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = {onNav()}) {
                    Icon(navIcon, contentDescription = "Nav icon")
                }
            }
        }
    )
}

@Composable
@MiniTalesPreview
fun AppbarPreview(modifier: Modifier = Modifier) {
    MiniTalesTheme {
        Surface {
            Appbar(
                title = "Minitales",
                navIcon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    }
}