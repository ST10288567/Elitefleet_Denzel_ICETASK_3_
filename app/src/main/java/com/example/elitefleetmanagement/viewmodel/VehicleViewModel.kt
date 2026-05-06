package com.example.elitefleetmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elitefleetmanagement.data.api.RetrofitClient
import com.example.elitefleetmanagement.data.model.Vehicle
import com.example.elitefleetmanagement.data.repository.FleetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class VehicleUiState(
    val vehicles: List<Vehicle> = emptyList(),
    val selectedVehicle: Vehicle? = null,
    val searchText: String = "",
    val selectedStatus: String = "All",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class VehicleViewModel : ViewModel() {

    private val repository = FleetRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(VehicleUiState())
    val uiState: StateFlow<VehicleUiState> = _uiState

    val filteredVehicles: List<Vehicle>
        get() {
            val state = _uiState.value

            return state.vehicles.filter { vehicle ->
                val matchesSearch =
                    vehicle.name.contains(state.searchText, ignoreCase = true) ||
                            vehicle.brand.contains(state.searchText, ignoreCase = true) ||
                            vehicle.branch.contains(state.searchText, ignoreCase = true)

                val matchesStatus =
                    state.selectedStatus == "All" ||
                            vehicle.status.equals(state.selectedStatus, ignoreCase = true)

                matchesSearch && matchesStatus
            }
        }

    fun loadVehicles() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

                val vehicles = repository.getVehicles()

                _uiState.value = _uiState.value.copy(
                    vehicles = vehicles,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Could not load vehicles. Please check that Docker API is running."
                )
            }
        }
    }

    fun loadVehicleById(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val vehicle = repository.getVehicleById(id)

                _uiState.value = _uiState.value.copy(
                    selectedVehicle = vehicle,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Could not load vehicle details."
                )
            }
        }
    }

    fun updateSearchText(value: String) {
        _uiState.value = _uiState.value.copy(searchText = value)
    }

    fun updateSelectedStatus(value: String) {
        _uiState.value = _uiState.value.copy(selectedStatus = value)
    }

    fun addVehicle(vehicle: Vehicle, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.addVehicle(vehicle)
                loadVehicles()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Vehicle could not be added."
                )
            }
        }
    }

    fun updateVehicle(vehicle: Vehicle, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.updateVehicle(vehicle.id, vehicle)
                loadVehicles()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Vehicle could not be updated."
                )
            }
        }
    }

    fun deleteVehicle(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteVehicle(id)
                loadVehicles()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Vehicle could not be deleted."
                )
            }
        }
    }
}