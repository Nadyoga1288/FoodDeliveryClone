package com.nadyoga.fooddelivery.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.repository.FoodRepository
import com.nadyoga.fooddelivery.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val repository: FoodRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<UiState<List<Restaurant>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Restaurant>>> = _uiState.asStateFlow()
    
    init {
        loadRestaurants()
    }
    
    private fun loadRestaurants() {
        viewModelScope.launch {
            try {
                repository.getRestaurants().collect { restaurants ->
                    _uiState.value = UiState.Success(restaurants)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e)
            }
        }
    }
    
    fun refresh() {
        _uiState.value = UiState.Loading
        loadRestaurants()
    }
}
