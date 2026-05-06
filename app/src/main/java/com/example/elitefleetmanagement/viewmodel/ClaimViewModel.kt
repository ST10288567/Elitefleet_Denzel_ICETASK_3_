package com.example.elitefleetmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elitefleetmanagement.data.api.RetrofitClient
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.data.repository.FleetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ClaimUiState(
    val claims: List<Claim> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ClaimViewModel : ViewModel() {

    private val repository = FleetRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(ClaimUiState())
    val uiState: StateFlow<ClaimUiState> = _uiState

    fun loadClaims() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val claims = repository.getClaims()

                _uiState.value = _uiState.value.copy(
                    claims = claims,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Could not load claims."
                )
            }
        }
    }

    fun updateClaimStatus(claim: Claim, status: String) {
        viewModelScope.launch {
            try {
                repository.updateClaim(
                    claim.id,
                    claim.copy(status = status)
                )
                loadClaims()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Could not update claim."
                )
            }
        }
    }
}