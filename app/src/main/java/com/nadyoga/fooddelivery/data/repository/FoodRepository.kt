package com.nadyoga.fooddelivery.data.repository

import com.nadyoga.fooddelivery.data.api.MenuData
import com.nadyoga.fooddelivery.data.api.model.MenuItem
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FoodRepository {
    
    fun getRestaurants(): Flow<List<Restaurant>> = flowOf(getTurkuRestaurants())
    
    fun getMenuItems(restaurantType: RestaurantType): Flow<List<MenuItem>> {
        return flowOf(MenuData.getMenuItems(restaurantType))
    }
    
    private fun getTurkuRestaurants(): List<Restaurant> = listOf(
        // Pizzerias in Turku
        Restaurant(
            id = 1,
            name = "Pizzeria Antonio",
            description = "Authentic Italian pizza in Turku city center",
            type = RestaurantType.PIZZA,
            imageUrl = "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=400&h=300&fit=crop",
            rating = 4.7,
            deliveryTime = "25-35 min",
            deliveryFee = "2.90€"
        ),
        Restaurant(
            id = 2,
            name = "Kahvila Nekala",
            description = "Cozy café with delicious homemade pizzas",
            type = RestaurantType.PIZZA,
            imageUrl = "https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400&h=300&fit=crop",
            rating = 4.5,
            deliveryTime = "20-30 min",
            deliveryFee = "1.90€"
        ),
        
        // Sushi places in Turku
        Restaurant(
            id = 3,
            name = "Yami Sushi",
            description = "Premium Japanese cuisine in Turku Market Square",
            type = RestaurantType.SUSHI,
            imageUrl = "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=400&h=300&fit=crop",
            rating = 4.8,
            deliveryTime = "30-40 min",
            deliveryFee = "3.90€"
        ),
        Restaurant(
            id = 4,
            name = "Sushi & Co",
            description = "Modern sushi bar with fresh ingredients",
            type = RestaurantType.SUSHI,
            imageUrl = "https://images.unsplash.com/photo-1553621045-f92e00ea2e81?w=400&h=300&fit=crop",
            rating = 4.6,
            deliveryTime = "25-35 min",
            deliveryFee = "2.90€"
        ),
        Restaurant(
            id = 5,
            name = "Umami Japanilainen Ravintola",
            description = "Traditional Japanese dining experience",
            type = RestaurantType.SUSHI,
            imageUrl = "https://images.unsplash.com/photo-1606495909903-584c4c5d5d9c?w=400&h=300&fit=crop",
            rating = 4.4,
            deliveryTime = "35-45 min",
            deliveryFee = "3.50€"
        ),
        
        // Burger places in Turku
        Restaurant(
            id = 6,
            name = "Burger King Aura",
            description = "Classic burgers near Aura River",
            type = RestaurantType.BURGER,
            imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=400&h=300&fit=crop",
            rating = 4.2,
            deliveryTime = "15-25 min",
            deliveryFee = "1.90€"
        ),
        Restaurant(
            id = 7,
            name = "Hesburger Kauppiaskatu",
            description = "Finnish favorite in central Turku",
            type = RestaurantType.BURGER,
            imageUrl = "https://images.unsplash.com/photo-1563612456674-4c7a1a015c7d?w=400&h=300&fit=crop",
            rating = 4.3,
            deliveryTime = "20-30 min",
            deliveryFee = "2.50€"
        ),
        Restaurant(
            id = 8,
            name = "Friends & Brgrs",
            description = "Gourmet burgers with local ingredients",
            type = RestaurantType.BURGER,
            imageUrl = "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=400&h=300&fit=crop",
            rating = 4.6,
            deliveryTime = "25-35 min",
            deliveryFee = "3.90€"
        )
    )
}
