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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitefleetmanagement.data.model.Vehicle
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.viewmodel.VehicleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleFormScreen(
    vehicleId: Int? = null,
    onBack: () -> Unit,
    viewModel: VehicleViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var name by remember { mutableStateOf("") }
    var brand by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Available") }
    var mileage by remember { mutableStateOf("") }
    var branch by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    val isEditing = vehicleId != null

    LaunchedEffect(vehicleId) {
        if (vehicleId != null) {
            viewModel.loadVehicleById(vehicleId)
        }
    }

    LaunchedEffect(state.selectedVehicle) {
        val vehicle = state.selectedVehicle

        if (vehicle != null && isEditing) {
            name = vehicle.name
            brand = vehicle.brand
            price = vehicle.price.toInt().toString()
            status = vehicle.status
            mileage = vehicle.mileage.toString()
            branch = vehicle.branch
            imageUrl = vehicle.imageUrl
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEditing) "Edit Vehicle" else "Add Vehicle"
                    )
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
            VehicleTextField("Vehicle Name", name) { name = it }
            VehicleTextField("Brand", brand) { brand = it }
            VehicleTextField("Price", price) { price = it }
            VehicleTextField("Status", status) { status = it }
            VehicleTextField("Mileage", mileage) { mileage = it }
            VehicleTextField("Branch", branch) { branch = it }
            VehicleTextField("Image URL", imageUrl) { imageUrl = it }

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                    val vehicle = Vehicle(
                        id = vehicleId ?: 0,
                        name = name,
                        brand = brand,
                        price = price.toDoubleOrNull() ?: 0.0,
                        status = status,
                        mileage = mileage.toIntOrNull() ?: 0,
                        branch = branch,
                        imageUrl = imageUrl
                    )

                    if (isEditing) {
                        viewModel.updateVehicle(vehicle) {
                            onBack()
                        }
                    } else {
                        viewModel.addVehicle(vehicle) {
                            onBack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (isEditing) "Save Changes" else "Add Vehicle"
                )
            }
        }
    }
}

@Composable
private fun VehicleTextField(
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