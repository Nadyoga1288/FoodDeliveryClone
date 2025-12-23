package com.nadyoga.fooddelivery.data.api.model

sealed class RestaurantType {
    data object PIZZA : RestaurantType()
    data object SUSHI : RestaurantType()
    data object BURGER : RestaurantType()

    companion object {
        fun values(): Array<RestaurantType> = arrayOf(PIZZA, SUSHI, BURGER)

        fun valueOf(value: String): RestaurantType = when (value.uppercase()) {
            "PIZZA" -> PIZZA
            "SUSHI" -> SUSHI
            "BURGER" -> BURGER
            else -> throw IllegalArgumentException("No RestaurantType for $value")
        }
    }
}