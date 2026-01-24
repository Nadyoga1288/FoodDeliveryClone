package com.nadyoga.fooddelivery.data.api.model

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val type: RestaurantType,
    val imageUrl: String = "",
    val rating: Double = 4.5,
    val deliveryTime: String = "20-30 min",
    val deliveryFee: String = "2.90€"
)
