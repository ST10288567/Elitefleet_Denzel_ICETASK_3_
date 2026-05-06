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

    suspend fun getVehicleById(id: Int): Vehicle {
        return api.getVehicleById(id)
    }

    suspend fun addVehicle(vehicle: Vehicle): Vehicle {
        return api.addVehicle(vehicle)
    }

    suspend fun updateVehicle(id: Int, vehicle: Vehicle): Vehicle {
        return api.updateVehicle(id, vehicle)
    }

    suspend fun deleteVehicle(id: Int) {
        api.deleteVehicle(id)
    }

    suspend fun getBookings(): List<Booking> {
        return api.getBookings()
    }

    suspend fun getClaims(): List<Claim> {
        return api.getClaims()
    }
}