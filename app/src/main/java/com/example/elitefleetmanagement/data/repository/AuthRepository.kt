package com.example.elitefleetmanagement.data.repository

import com.example.elitefleetmanagement.data.api.EliteFleetApi
import com.example.elitefleetmanagement.data.model.LoginRequest
import com.example.elitefleetmanagement.data.model.LoginResponse

class AuthRepository(
    private val api: EliteFleetApi
) {
    suspend fun login(email: String, password: String): LoginResponse {
        return api.login(LoginRequest(email, password))
    }
}