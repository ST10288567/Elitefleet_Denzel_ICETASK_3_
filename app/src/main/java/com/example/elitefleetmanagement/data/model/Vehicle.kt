package com.example.elitefleetmanagement.data.model

data class Vehicle(
    val id: Int,
    val name: String,
    val brand: String,
    val price: Double,
    val status: String,
    val mileage: Int,
    val branch: String,
    val imageUrl: String
)