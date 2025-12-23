package com.nadyoga.fooddelivery.ui.screens.restaurant_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import com.nadyoga.fooddelivery.ui.components.RestaurantCard
import com.nadyoga.fooddelivery.ui.screens.menu.MenuScreen
import com.nadyoga.fooddelivery.utils.UiState
import com.nadyoga.fooddelivery.viewmodel.RestaurantListViewModel

@Composable
fun RestaurantListScreen(
    viewModel: RestaurantListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    var selectedRestaurantType by remember { mutableStateOf<RestaurantType?>(null) }

    when (state) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UiState.Success -> {
            val restaurants = (state as UiState.Success<List<Restaurant>>).data

            if (selectedRestaurantType == null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(top = 200.dp, bottom = 24.dp)
                ) {
                    items(restaurants) { restaurant ->
                        RestaurantCard(
                            restaurant = restaurant,
                            onClick = { selectedRestaurantType = restaurant.type }
                        )
                    }
                }
            } else {
                MenuScreen(
                    selectedType = selectedRestaurantType!!,
                    onBackClick = { selectedRestaurantType = null },
                )
            }
        }

        is UiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = (state as UiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}