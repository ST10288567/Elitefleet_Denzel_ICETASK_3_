package com.example.elitefleetmanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardBackspace
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingFormScreen(
    onBack: () -> Unit,
    viewModel: BookingViewModel = viewModel()
) {
    var customerName by remember { mutableStateOf("") }
    var customerEmail by remember { mutableStateOf("") }
    var vehicleName by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var consultant by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Create Appointment")
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
                .verticalScroll(rememberScrollState())
        ) {
            BookingTextField("Customer Name", customerName) { customerName = it }
            BookingTextField("Customer Email", customerEmail) { customerEmail = it }
            BookingTextField("Vehicle Name", vehicleName) { vehicleName = it }
            BookingTextField("Date e.g. 2026-05-20", date) { date = it }
            BookingTextField("Time e.g. 10:30", time) { time = it }
            BookingTextField("Sales Consultant", consultant) { consultant = it }

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                    val booking = Booking(
                        id = 0,
                        customerName = customerName,
                        customerEmail = customerEmail,
                        vehicleName = vehicleName,
                        date = date,
                        time = time,
                        consultant = consultant,
                        status = "Pending"
                    )

                    viewModel.addBooking(booking) {
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create Appointment")
            }
        }
    }
}

@Composable
private fun BookingTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        singleLine = true
    )
}