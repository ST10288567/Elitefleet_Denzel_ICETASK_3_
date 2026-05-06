package com.example.elitefleetmanagement.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.ui.theme.FleetBlue
import com.example.elitefleetmanagement.ui.theme.FleetDanger
import com.example.elitefleetmanagement.ui.theme.FleetSuccess
import com.example.elitefleetmanagement.ui.theme.FleetTextGrey
import com.example.elitefleetmanagement.ui.theme.FleetWarning

@Composable
fun BookingCard(
    booking: Booking,
    onClick: () -> Unit
) {
    val statusColor = when (booking.status) {
        "Confirmed" -> FleetSuccess
        "Pending" -> FleetWarning
        "Cancelled" -> FleetDanger
        else -> FleetBlue
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.CalendarMonth,
                contentDescription = null,
                tint = FleetBlue
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = booking.customerName,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = booking.vehicleName,
                    color = FleetTextGrey
                )

                Text(
                    text = "${booking.date} at ${booking.time}",
                    color = FleetTextGrey
                )
            }

            AssistChip(
                onClick = {},
                label = {
                    Text(booking.status)
                },
                colors = AssistChipDefaults.assistChipColors(
                    labelColor = statusColor
                )
            )
        }
    }
}