package com.nadyoga.fooddelivery.data.api

import com.nadyoga.fooddelivery.data.api.model.MenuItem
import com.nadyoga.fooddelivery.data.api.model.RestaurantType

fun getMenuFor(type: RestaurantType): List<MenuItem> {
    return when (type) {
        RestaurantType.PIZZA -> listOf(
            MenuItem(1, "🍕", "Margherita", "€8.50"),
            MenuItem(2, "🍕", "Pepperoni", "€9.50"),
            MenuItem(3, "🍕", "Four Cheese", "€10.00")
        )
        RestaurantType.SUSHI -> listOf(
            MenuItem(1, "🍣", "California Roll", "€12.00"),
            MenuItem(2, "🍣", "Spicy Tuna", "€13.50"),
            MenuItem(3, "🍣", "Salmon Nigiri", "€11.00")
        )
        RestaurantType.BURGER -> listOf(
            MenuItem(1, "🍔", "Classic Burger", "€7.50"),
            MenuItem(2, "🍔", "Cheeseburger", "€8.50"),
            MenuItem(3, "🍔", "Bacon Burger", "€9.50")
        )
    }
}