package com.example.elitefleetmanagement.data.repository

import com.example.elitefleetmanagement.data.api.EliteFleetApi
import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.data.model.Vehicle

class FleetRepository(
    private val api: EliteFleetApi
) {
    suspend fun getVehicles(): List<Vehicle> {
        return api.getVehicles()
    }

    suspend fun getBookings(): List<Booking> {
        return api.getBookings()
    }

    suspend fun getClaims(): List<Claim> {
        return api.getClaims()
    }
}