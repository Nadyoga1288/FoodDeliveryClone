
package com.nadyoga.fooddelivery.viewmodel
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import androidx.lifecycle.ViewModel
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RestaurantListViewModel : ViewModel() {

    private val _state =
        MutableStateFlow<UiState<List<Restaurant>>>(UiState.Loading)

    val state: StateFlow<UiState<List<Restaurant>>> = _state

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        val fakeData = listOf(
            Restaurant(
                id = 1,
                name = "Pizza Place",
                description = "Best pizza in town",
                imageUrl = "",
                type = RestaurantType.PIZZA
            ),
            Restaurant(
                id = 2,
                name = "Sushi Bar",
                description = "Fresh sushi daily",
                imageUrl = "",
                type = RestaurantType.SUSHI
            ),
            Restaurant(
                id = 3,
                name = "Burger House",
                description = "Tasty burgers",
                imageUrl = "",
                type = RestaurantType.BURGER
            )
        )

        _state.value = UiState.Success(fakeData)
    }
}
