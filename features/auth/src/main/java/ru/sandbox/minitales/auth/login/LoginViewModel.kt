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
import ru.sandbox.minitales.auth.validators.AuthParam
import ru.sandbox.minitales.auth.validators.ValidatorFactory
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorFactory: ValidatorFactory
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
                if (areInputsValid()) {
                    login()
                }
            }

            else -> {

            }
        }
    }

    private fun areInputsValid(): Boolean {
        val email = uiState.value.email
        val password = uiState.value.password
        val emailError = validatorFactory.get(AuthParam.EMAIL).validate(email)
        val passwordError = validatorFactory.get(AuthParam.PASSWORD).validate(password)

        _uiState.value = _uiState.value.copy(
            emailError = emailError.errorMessage,
            passwordError = passwordError.errorMessage
        )
        val hasError = listOf(emailError, passwordError).any { it.isValid.not() }
        return hasError.not()
    }

    fun login() = viewModelScope.launch {
        with(uiState.value) {
            loginUseCase.invoke(email, password)
        }
    }
}