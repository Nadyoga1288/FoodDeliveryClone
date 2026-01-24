package com.nadyoga.fooddelivery.ui.screens.restaurant_list

import androidx.lifecycle.ViewModel
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantListViewModel : ViewModel() {
    private val _restaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _restaurants

    init {
        // Заповнюємо тестовими даними
        _restaurants.value = listOf(
            Restaurant(1, "Pizza House", "Best pizza in Turku", RestaurantType.PIZZA),
            Restaurant(2, "Tokyo Sushi", "Fresh rolls", RestaurantType.SUSHI),
            Restaurant(3, "Burger King", "Fast and tasty", RestaurantType.BURGER)
        )
    }
}
