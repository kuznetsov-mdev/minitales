package ru.sandbox.minitales.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sandbox.minitales.auth.domain.LoginUseCase
import ru.sandbox.minitales.auth.login.data.LoginUiEvent
import ru.sandbox.minitales.auth.login.data.LoginUiState
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
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

            is LoginUiEvent.Login -> {
                login()
            }

            else -> {

            }
        }
    }

    fun login() = viewModelScope.launch {
        with(uiState.value) {
            loginUseCase.invoke(email, password)
        }
    }
}