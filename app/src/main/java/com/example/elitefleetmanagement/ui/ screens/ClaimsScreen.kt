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
import androidx.compose.material.icons.rounded.Assignment
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetGrey
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey
import com.example.elitefleetmanagement.viewmodel.ClaimViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClaimsScreen(
    viewModel: ClaimViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadClaims()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Claims Workflow", fontWeight = FontWeight.Bold)
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
                text = "Review, approve and reject customer vehicle-related claims.",
                color = FleetTextGrey
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                state.errorMessage != null -> {
                    Text(state.errorMessage ?: "")
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(state.claims) { claim ->
                            ClaimCard(
                                claim = claim,
                                onApprove = {
                                    viewModel.updateClaimStatus(claim, "Approved")
                                },
                                onReject = {
                                    viewModel.updateClaimStatus(claim, "Rejected")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ClaimCard(
    claim: Claim,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Assignment,
                    contentDescription = null,
                    tint = FleetBlue
                )

                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = claim.title,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = claim.customerName,
                        color = FleetTextGrey
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text("Amount: R ${claim.amount.toInt()}")
            Text("Date: ${claim.date}")
            Text("Status: ${claim.status}")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onApprove,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Approve")
                }

                FilledTonalButton(
                    onClick = onReject,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Reject")
                }
            }
        }
    }
}