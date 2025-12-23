package com.nadyoga.fooddelivery.data.api.model

//enum class RestaurantType {


data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val type: RestaurantType
)
