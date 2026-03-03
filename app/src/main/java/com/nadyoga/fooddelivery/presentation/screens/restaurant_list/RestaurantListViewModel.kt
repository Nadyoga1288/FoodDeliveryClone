package com.nadyoga.fooddelivery.presentation.screens.restaurant_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nadyoga.fooddelivery.domain.model.Restaurant
import com.nadyoga.fooddelivery.domain.usecase.GetRestaurantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantsUseCase: GetRestaurantsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RestaurantListUiState>(RestaurantListUiState.Loading)
    val uiState: StateFlow<RestaurantListUiState> = _uiState.asStateFlow()

    init {
        loadRestaurants()
    }

    fun loadRestaurants() {
        viewModelScope.launch {
            _uiState.value = RestaurantListUiState.Loading
            getRestaurantsUseCase()
                .catch { e ->
                    _uiState.value = RestaurantListUiState.Error(e.message ?: "Unknown error")
                }
                .collect { restaurants ->
                    _uiState.value = RestaurantListUiState.Success(restaurants)
                }
        }
    }
}

sealed interface RestaurantListUiState {
    data object Loading : RestaurantListUiState
    data class Success(val restaurants: List<Restaurant>) : RestaurantListUiState
    data class Error(val message: String) : RestaurantListUiState
}
