package com.example.elitefleetmanagement.data.api

import com.example.elitefleetmanagement.data.model.Booking
import com.example.elitefleetmanagement.data.model.Claim
import com.example.elitefleetmanagement.data.model.LoginRequest
import com.example.elitefleetmanagement.data.model.LoginResponse
import com.example.elitefleetmanagement.data.model.Vehicle
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EliteFleetApi {

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("vehicles")
    suspend fun getVehicles(): List<Vehicle>

    @GET("bookings")
    suspend fun getBookings(): List<Booking>

    @GET("claims")
    suspend fun getClaims(): List<Claim>
}