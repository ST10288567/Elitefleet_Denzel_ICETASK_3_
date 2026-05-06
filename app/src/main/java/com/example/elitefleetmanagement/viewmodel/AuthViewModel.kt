package com.example.elitefleetmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elitefleetmanagement.data.api.RetrofitClient
import com.example.elitefleetmanagement.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val userName: String = ""
)

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun login() {
        val current = _uiState.value

        if (current.email.isBlank() || current.password.isBlank()) {
            _uiState.value = current.copy(errorMessage = "Please enter your email and password.")
            return
        }

        viewModelScope.launch {
            try {
                _uiState.value = current.copy(isLoading = true, errorMessage = null)

                val response = repository.login(
                    email = current.email,
                    password = current.password
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isLoggedIn = response.success,
                    userName = response.name
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Login failed. Please make sure Docker API is running."
                )
            }
        }
    }
}