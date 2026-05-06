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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.elitefleetmanagement.ui.components.SectionTitle
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey

@Composable
fun DashboardScreen(
    onInventoryClick: () -> Unit,
    onBookingsClick: () -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Rounded.DirectionsCar, null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Rounded.CalendarMonth, null) },
                    label = { Text("Bookings") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Rounded.Assignment, null) },
                    label = { Text("Claims") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Rounded.Person, null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FleetGrey)
                .padding(padding)
                .padding(20.dp)
        ) {
            Text(
                text = "Dashboard",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Elite Fleet Management",
                color = FleetTextGrey
            )

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DashboardStatCard(
                    title = "Vehicles",
                    value = "24",
                    icon = Icons.Rounded.DirectionsCar,
                    modifier = Modifier.weight(1f)
                )

                DashboardStatCard(
                    title = "Bookings",
                    value = "12",
                    icon = Icons.Rounded.CalendarMonth,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DashboardStatCard(
                    title = "Claims",
                    value = "5",
                    icon = Icons.Rounded.Assignment,
                    modifier = Modifier.weight(1f)
                )

                DashboardStatCard(
                    title = "Reports",
                    value = "8",
                    icon = Icons.Rounded.BarChart,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            SectionTitle(text = "System Modules")

            Spacer(modifier = Modifier.height(12.dp))

            ModuleCard(
                title = "Vehicle Inventory",
                description = "Manage vehicle listings, availability and details.",
                onClick = onInventoryClick
            )
            ModuleCard(
                title = "Appointment Booking",
                description = "Book vehicle viewings and assign consultants.",
                onClick = onBookingsClick
            )
            ModuleCard("Claims Workflow", "Review customer claims and track claim status.")
            ModuleCard("Reports", "View dealership insights and export summaries.")
        }
    }
}

@Composable
private fun DashboardStatCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = FleetBlue
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = title,
                color = FleetTextGrey
            )
        }
    }
}

@Composable
private fun ModuleCard(
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                color = FleetTextGrey,
                fontSize = 13.sp
            )
        }
    }
}