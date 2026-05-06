package com.example.elitefleetmanagement.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Profile", fontWeight = FontWeight.Bold)
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
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null,
                        tint = FleetBlue
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Admin User",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Administrator",
                        color = FleetTextGrey
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ProfileInfoCard("Branch", "Durban Central")
            ProfileInfoCard("Role", "System Administrator")
            ProfileInfoCard("System", "Elite Fleet Management")
            ProfileInfoCard("API Status", "Connected through Docker REST API")
        }
    }
}

@Composable
private fun ProfileInfoCard(
    title: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                color = FleetTextGrey
            )

            Text(
                text = value,
                fontWeight = FontWeight.Bold
            )
        }
    }
}