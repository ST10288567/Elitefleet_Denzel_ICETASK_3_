package com.example.elitefleetmanagement.data.model

data class Booking(
    val id: Int,
    val customerName: String,
    val customerEmail: String,
    val vehicleName: String,
    val date: String,
    val time: String,
    val consultant: String,
    val status: String
)