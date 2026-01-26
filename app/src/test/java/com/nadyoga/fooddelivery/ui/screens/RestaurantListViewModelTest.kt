package com.nadyoga.fooddelivery.ui.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import com.nadyoga.fooddelivery.data.repository.FoodRepository
import com.nadyoga.fooddelivery.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RestaurantListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: FoodRepository

    private lateinit var viewModel: RestaurantListViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = RestaurantListViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadRestaurants should emit loading then success state`() = runTest {
        // Given
        val restaurants = listOf(
            Restaurant(1, "Test Restaurant", "Test Description", RestaurantType.PIZZA)
        )
        `when`(repository.getRestaurants()).thenReturn(flow { emit(restaurants) })

        // When
        viewModel = RestaurantListViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Success)
        assertEquals(restaurants, (state as UiState.Success).data)
    }

    @Test
    fun `loadRestaurants should emit error when repository throws exception`() = runTest {
        // Given
        val exception = Exception("Network error")
        `when`(repository.getRestaurants()).thenThrow(exception)

        // When
        viewModel = RestaurantListViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Error)
        assertEquals(exception, (state as UiState.Error).exception)
    }

    @Test
    fun `refresh should reload restaurants`() = runTest {
        // Given
        val restaurants = listOf(
            Restaurant(1, "Test Restaurant", "Test Description", RestaurantType.PIZZA)
        )
        `when`(repository.getRestaurants()).thenReturn(flow { emit(restaurants) })

        // When
        viewModel.refresh()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(repository).getRestaurants()
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Success)
    }
}
