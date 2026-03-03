package com.nadyoga.fooddelivery.presentation.screens.restaurant_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nadyoga.fooddelivery.presentation.components.RestaurantCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantListScreen(
    viewModel: RestaurantListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Wolt Food Clone",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is RestaurantListUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is RestaurantListUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(state.restaurants) { restaurant ->
                            RestaurantCard(
                                restaurant = restaurant,
                                onClick = { /* Navigate to detail */ }
                            )
                        }
                    }
                }
                is RestaurantListUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
