package com.example.elitefleetmanagement.data.api

import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.data.model.LoginRequest
import com.example.elitefleetmanagement.data.model.LoginResponse
import com.example.elitefleetmanagement.data.model.Vehicle
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EliteFleetApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("vehicles")
    suspend fun getVehicles(): List<Vehicle>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(
        @Path("id") id: Int
    ): Vehicle

    @POST("vehicles")
    suspend fun addVehicle(
        @Body vehicle: Vehicle
    ): Vehicle

    @PUT("vehicles/{id}")
    suspend fun updateVehicle(
        @Path("id") id: Int,
        @Body vehicle: Vehicle
    ): Vehicle

    @DELETE("vehicles/{id}")
    suspend fun deleteVehicle(
        @Path("id") id: Int
    )

    @GET("bookings")
    suspend fun getBookings(): List<Booking>

    @GET("claims")
    suspend fun getClaims(): List<Claim>
}