
package com.nadyoga.fooddelivery.ui.theme.screens.restaurant_list


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadyoga.fooddelivery.ui.components.RestaurantCard
import com.nadyoga.fooddelivery.utils.UiState
import com.nadyoga.fooddelivery.viewmodel.RestaurantListViewModel

@Composable
fun RestaurantListScreen(
    viewModel: RestaurantListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val restaurants = (state as UiState.Success).data

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(restaurants) { restaurant ->
                    RestaurantCard(restaurant)
                }
            }
        }

        is UiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error loading restaurants")
            }
        }
    }
}