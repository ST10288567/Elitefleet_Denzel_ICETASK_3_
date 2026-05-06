package com.example.elitefleetmanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitefleetmanagement.ui.components.BookingCard
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey
import com.example.elitefleetmanagement.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    onAddBooking: () -> Unit,
    onBookingClick: (Int) -> Unit,
    viewModel: BookingViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBookings()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Appointments",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBooking
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add booking"
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FleetGrey)
                .padding(padding)
                .padding(18.dp)
        ) {
            Text(
                text = "Create and manage customer vehicle viewing appointments.",
                color = FleetTextGrey
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.searchText,
                onValueChange = viewModel::updateSearchText,
                label = {
                    Text("Search appointments")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("All", "Pending", "Confirmed", "Cancelled").forEach { status ->
                    AssistChip(
                        onClick = {
                            viewModel.updateSelectedStatus(status)
                        },
                        label = {
                            Text(status)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                state.errorMessage != null -> {
                    Text(text = state.errorMessage ?: "")
                }

                viewModel.filteredBookings.isEmpty() -> {
                    Text(text = "No appointments found.")
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(viewModel.filteredBookings) { booking ->
                            BookingCard(
                                booking = booking,
                                onClick = {
                                    onBookingClick(booking.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}