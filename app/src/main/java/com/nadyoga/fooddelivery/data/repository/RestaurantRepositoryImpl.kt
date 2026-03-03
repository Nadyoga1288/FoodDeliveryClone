package com.nadyoga.fooddelivery.data.repository

import com.nadyoga.fooddelivery.domain.model.Restaurant
import com.nadyoga.fooddelivery.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor() : RestaurantRepository {

    private val restaurants = listOf(
        Restaurant(
            id = "1",
            name = "Wolt Pizza Helsinki",
            description = "Authentic Italian pizza in the heart of Helsinki.",
            imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=800",
            rating = 4.9,
            deliveryFee = 1.90,
            deliveryTimeMinutes = 20,
            category = "Pizza"
        ),
        Restaurant(
            id = "2",
            name = "Sushi Master Espoo",
            description = "Fresh sushi and sashimi with premium ingredients.",
            imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=800",
            rating = 4.7,
            deliveryFee = 2.50,
            deliveryTimeMinutes = 35,
            category = "Sushi"
        ),
        Restaurant(
            id = "3",
            name = "Burger Joint Tampere",
            description = "Juicy burgers and crispy fries.",
            imageUrl = "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=800",
            rating = 4.5,
            deliveryFee = 0.00,
            deliveryTimeMinutes = 25,
            category = "Burger"
        )
    )

    override fun getRestaurants(): Flow<List<Restaurant>> = flowOf(restaurants)

    override suspend fun getRestaurantById(id: String): Restaurant? {
        return restaurants.find { it.id == id }
    }

    override suspend fun toggleFavorite(id: String) {
        // Implementation for local DB
    }

    override fun searchRestaurants(query: String): Flow<List<Restaurant>> {
        return flowOf(restaurants.filter { it.name.contains(query, ignoreCase = true) })
    }
}
