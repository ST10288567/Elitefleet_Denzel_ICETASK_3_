package com.example.elitefleetmanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elitefleetmanagement.data.api.RetrofitClient
import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.data.repository.FleetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class BookingUiState(
    val bookings: List<Booking> = emptyList(),
    val selectedBooking: Booking? = null,
    val searchText: String = "",
    val selectedStatus: String = "All",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class BookingViewModel : ViewModel() {

    private val repository = FleetRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState

    val filteredBookings: List<Booking>
        get() {
            val state = _uiState.value

            return state.bookings.filter { booking ->
                val matchesSearch =
                    booking.customerName.contains(state.searchText, ignoreCase = true) ||
                            booking.vehicleName.contains(state.searchText, ignoreCase = true) ||
                            booking.consultant.contains(state.searchText, ignoreCase = true)

                val matchesStatus =
                    state.selectedStatus == "All" ||
                            booking.status.equals(state.selectedStatus, ignoreCase = true)

                matchesSearch && matchesStatus
            }
        }

    fun loadBookings() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null
                )

                val bookings = repository.getBookings()

                _uiState.value = _uiState.value.copy(
                    bookings = bookings,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Could not load bookings. Please check that Docker API is running."
                )
            }
        }
    }

    fun loadBookingById(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val booking = repository.getBookingById(id)

                _uiState.value = _uiState.value.copy(
                    selectedBooking = booking,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Could not load booking details."
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

    fun addBooking(booking: Booking, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.addBooking(booking)
                loadBookings()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Booking could not be created."
                )
            }
        }
    }

    fun updateBooking(booking: Booking, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.updateBooking(booking.id, booking)
                loadBookings()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Booking could not be updated."
                )
            }
        }
    }

    fun deleteBooking(id: Int, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.deleteBooking(id)
                loadBookings()
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Booking could not be deleted."
                )
            }
        }
    }
}