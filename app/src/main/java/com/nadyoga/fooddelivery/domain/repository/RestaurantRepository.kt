package com.nadyoga.fooddelivery.domain.repository

import com.nadyoga.fooddelivery.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurants(): Flow<List<Restaurant>>
    suspend fun getRestaurantById(id: String): Restaurant?
    suspend fun toggleFavorite(id: String)
    fun searchRestaurants(query: String): Flow<List<Restaurant>>
}
