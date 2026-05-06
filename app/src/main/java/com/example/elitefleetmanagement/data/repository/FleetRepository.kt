package com.example.elitefleetmanagement.data.repository

import com.example.elitefleetmanagement.data.api.EliteFleetApi
import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.data.model.Vehicle

class FleetRepository(
    private val api: EliteFleetApi
) {

    // VEHICLES CRUD
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

    // BOOKINGS CRUD
    suspend fun getBookings(): List<Booking> {
        return api.getBookings()
    }

    suspend fun getBookingById(id: Int): Booking {
        return api.getBookingById(id)
    }

    suspend fun addBooking(booking: Booking): Booking {
        return api.addBooking(booking)
    }

    suspend fun updateBooking(id: Int, booking: Booking): Booking {
        return api.updateBooking(id, booking)
    }

    suspend fun deleteBooking(id: Int) {
        api.deleteBooking(id)
    }

    // CLAIMS
    suspend fun getClaims(): List<Claim> {
        return api.getClaims()
    }

    suspend fun updateClaim(id: Int, claim: Claim): Claim {
        return api.updateClaim(id, claim)
    }
}