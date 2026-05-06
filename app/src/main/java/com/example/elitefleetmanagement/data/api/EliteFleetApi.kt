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

    // LOGIN
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    // VEHICLES CRUD
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

    // BOOKINGS CRUD
    @GET("bookings")
    suspend fun getBookings(): List<Booking>

    @GET("bookings/{id}")
    suspend fun getBookingById(
        @Path("id") id: Int
    ): Booking

    @POST("bookings")
    suspend fun addBooking(
        @Body booking: Booking
    ): Booking

    @PUT("bookings/{id}")
    suspend fun updateBooking(
        @Path("id") id: Int,
        @Body booking: Booking
    ): Booking

    @DELETE("bookings/{id}")
    suspend fun deleteBooking(
        @Path("id") id: Int
    )

    // CLAIMS
    @GET("claims")
    suspend fun getClaims(): List<Claim>

    @PUT("claims/{id}")
    suspend fun updateClaim(
        @Path("id") id: Int,
        @Body claim: Claim
    ): Claim
}