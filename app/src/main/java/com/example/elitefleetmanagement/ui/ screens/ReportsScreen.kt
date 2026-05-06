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
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Reports", fontWeight = FontWeight.Bold)
                }
            )
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
                text = "Dealership performance summary and operational insights.",
                color = FleetTextGrey
            )

            Spacer(modifier = Modifier.height(18.dp))

            ReportCard(
                title = "Vehicle Stock",
                value = "24",
                description = "Total vehicles currently recorded",
                icon = Icons.Rounded.DirectionsCar
            )

            ReportCard(
                title = "Appointments",
                value = "12",
                description = "Customer viewing appointments",
                icon = Icons.Rounded.CalendarMonth
            )

            ReportCard(
                title = "Claims",
                value = "5",
                description = "Claims currently in workflow",
                icon = Icons.Rounded.Assignment
            )

            ReportCard(
                title = "Monthly Revenue",
                value = "R 1.8M",
                description = "Estimated dealership revenue",
                icon = Icons.Rounded.BarChart
            )
        }
    }
}

@Composable
private fun ReportCard(
    title: String,
    value: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = FleetBlue
            )

            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = value,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = description,
                    color = FleetTextGrey
                )
            }
        }
    }
}