package com.nadyoga.fooddelivery.data.api.model

data class CartItem(
    val menuItem: MenuItem,
    var quantity: Int // Кількість одиниць
)
