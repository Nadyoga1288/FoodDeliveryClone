package com.nadyoga.fooddelivery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    
    private val _favoriteRestaurants = MutableStateFlow<Set<Int>>(emptySet())
    val favoriteRestaurants: StateFlow<Set<Int>> = _favoriteRestaurants.asStateFlow()
    
    fun toggleFavorite(restaurantId: Int) {
        viewModelScope.launch {
            val currentFavorites = _favoriteRestaurants.value.toMutableSet()
            if (currentFavorites.contains(restaurantId)) {
                currentFavorites.remove(restaurantId)
            } else {
                currentFavorites.add(restaurantId)
            }
            _favoriteRestaurants.value = currentFavorites
        }
    }
    
    fun isFavorite(restaurantId: Int): Boolean {
        return _favoriteRestaurants.value.contains(restaurantId)
    }
}
