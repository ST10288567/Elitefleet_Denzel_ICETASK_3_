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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.material.icons.rounded.TaskAlt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey
import com.example.elitefleetmanagement.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingDetailsScreen(
    bookingId: Int,
    onBack: () -> Unit,
    viewModel: BookingViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val booking = state.selectedBooking
    var showDeleteDialog by remember { mutableStateOf(false) }

    LaunchedEffect(bookingId) {
        viewModel.loadBookingById(bookingId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Appointment Details")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Rounded.KeyboardBackspace, null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FleetGrey)
                .padding(padding)
                .padding(20.dp)
        ) {
            if (booking != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.TaskAlt,
                            contentDescription = null,
                            tint = FleetBlue
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = booking.customerName,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = booking.customerEmail,
                            color = FleetTextGrey
                        )

                        Spacer(modifier = Modifier.height(22.dp))

                        BookingDetailRow("Vehicle", booking.vehicleName)
                        BookingDetailRow("Date", booking.date)
                        BookingDetailRow("Time", booking.time)
                        BookingDetailRow("Consultant", booking.consultant)
                        BookingDetailRow("Status", booking.status)

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = {
                                    val updated = booking.copy(status = "Confirmed")
                                    viewModel.updateBooking(updated) {
                                        viewModel.loadBookingById(booking.id)
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Confirm")
                            }

                            FilledTonalButton(
                                onClick = {
                                    val updated = booking.copy(status = "Cancelled")
                                    viewModel.updateBooking(updated) {
                                        viewModel.loadBookingById(booking.id)
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Cancel")
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        FilledTonalButton(
                            onClick = {
                                showDeleteDialog = true
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Rounded.Delete, null)
                            Text(" Delete Appointment")
                        }
                    }
                }
            } else {
                Text("Loading appointment details...")
            }
        }
    }

    if (showDeleteDialog && booking != null) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text("Delete Appointment")
            },
            text = {
                Text("Are you sure you want to delete this appointment?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteBooking(booking.id) {
                            showDeleteDialog = false
                            onBack()
                        }
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
private fun BookingDetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = FleetTextGrey
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold
        )
    }
}