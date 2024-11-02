package ru.sandbox.minitales.auth.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.sandbox.minitales.auth.login.data.LoginUiEvent
import ru.sandbox.minitales.auth.login.data.LoginUiState

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUiEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.update { _uiState.value.copy(email = uiEvent.email) }
            }

            is LoginUiEvent.PasswordChanged -> {
                _uiState.update { _uiState.value.copy(password = uiEvent.password) }
            }

            else -> {

            }
        }
    }
}