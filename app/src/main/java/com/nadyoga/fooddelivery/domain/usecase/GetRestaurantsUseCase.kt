package com.nadyoga.fooddelivery.domain.usecase

import com.nadyoga.fooddelivery.domain.model.Restaurant
import com.nadyoga.fooddelivery.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {
    operator fun invoke(): Flow<List<Restaurant>> {
        return repository.getRestaurants()
    }
}
