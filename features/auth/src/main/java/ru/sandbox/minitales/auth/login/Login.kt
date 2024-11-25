package ru.sandbox.minitales.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose.MiniTalesTheme
import ru.sandbox.minitales.auth.R
import ru.sandbox.minitales.auth.login.data.LoginUiEvent
import ru.sandbox.minitales.auth.login.data.LoginUiState
import ru.sandbox.minitales.theme.components.AppTextField
import ru.sandbox.minitales.theme.components.MiniTalesPreview

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Login(
        uiState = uiState.value,
        onEvent = { viewModel.onUiEvent(it) }
    )
}

@Composable
fun Login(
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 42.dp, bottom = 32.dp),
            painter = painterResource(R.drawable.mini_tales),
            contentDescription = "Mini tales"
        )

        AppTextField(
            value = uiState.email,
            label = R.string.email,
            error = uiState.emailError,
            hint = "yourname@domain.com",
            leadingIcon = Icons.Filled.Email,
            imeAction = ImeAction.Next,
            onValueChanged = { onEvent(LoginUiEvent.EmailChanged(it)) }
        )

        AppTextField(
            value = uiState.password,
            label = R.string.password,
            error = uiState.passwordError,
            isPasswordField = true,
            hint = "password",
            leadingIcon = Icons.Filled.Lock,
            imeAction = ImeAction.Done,
            onValueChanged = { onEvent(LoginUiEvent.PasswordChanged(it)) }
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = stringResource(R.string.click_here_to_reset),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                onClick = {
                    onEvent.invoke(LoginUiEvent.Login)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_forward),
                    contentDescription = "login"
                )
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
                .clickable { },
            text = stringResource(R.string.dont_have_account),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
                .clickable { },
            text = stringResource(R.string.agriment),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@MiniTalesPreview
fun LoggingPreview(modifier: Modifier = Modifier) {
    MiniTalesTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Login(
                uiState = LoginUiState(
                    email = "example@domain.com",
                    password = "12345"
                ),
                onEvent = {}
            )
        }
    }
}