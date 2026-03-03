package com.nadyoga.fooddelivery.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val rating: Double,
    val deliveryFee: Double,
    val deliveryTimeMinutes: Int,
    val category: String,
    val isFavorite: Boolean = false
)
