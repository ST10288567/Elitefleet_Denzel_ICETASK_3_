package com.example.elitefleetmanagement.data.model

data class LoginResponse(
    val success: Boolean,
    val userId: Int,
    val name: String,
    val role: String,
    val token: String
)