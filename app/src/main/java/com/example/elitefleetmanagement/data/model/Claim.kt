package com.example.elitefleetmanagement.data.model

data class Claim(
    val id: Int,
    val title: String,
    val customerName: String,
    val amount: Double,
    val status: String,
    val date: String
)