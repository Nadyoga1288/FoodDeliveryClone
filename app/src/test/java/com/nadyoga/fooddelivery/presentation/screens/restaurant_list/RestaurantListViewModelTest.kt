package com.nadyoga.fooddelivery.presentation.screens.restaurant_list

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.nadyoga.fooddelivery.domain.model.Restaurant
import com.nadyoga.fooddelivery.domain.usecase.GetRestaurantsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RestaurantListViewModelTest {

    private val getRestaurantsUseCase: GetRestaurantsUseCase = mockk()
    private lateinit var viewModel: RestaurantListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadRestaurants should update state to Success when use case returns data`() = runTest {
        // Given
        val restaurants = listOf(
            Restaurant(
                id = "1",
                name = "Wolt Pizza",
                description = "Best pizza in Helsinki",
                imageUrl = "https://example.com/pizza.jpg",
                rating = 4.8,
                deliveryFee = 1.90,
                deliveryTimeMinutes = 25,
                category = "Pizza"
            )
        )
        coEvery { getRestaurantsUseCase() } returns flowOf(restaurants)

        // When
        viewModel = RestaurantListViewModel(getRestaurantsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertThat(state).isInstanceOf(RestaurantListUiState.Success::class.java)
            assertThat((state as RestaurantListUiState.Success).restaurants).isEqualTo(restaurants)
        }
    }
}
