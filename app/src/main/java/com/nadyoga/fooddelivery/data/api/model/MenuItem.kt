package com.nadyoga.fooddelivery.data.api.model

data class MenuItem(
    val id: Int,
    val name: String,
    val price: String,
    val icon: String,
    val imageUrl: String = "",
    val description: String = "",
    val category: String = ""
)
