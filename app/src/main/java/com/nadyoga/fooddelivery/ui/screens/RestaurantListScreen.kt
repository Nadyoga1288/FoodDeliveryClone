package com.nadyoga.fooddelivery.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import com.nadyoga.fooddelivery.ui.components.MainScaffold
import com.nadyoga.fooddelivery.ui.components.RestaurantCard
import com.nadyoga.fooddelivery.ui.FavoritesViewModel
import com.nadyoga.fooddelivery.ui.screens.auth.AuthViewModel

@Composable
fun RestaurantListScreen(
    restaurants: List<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit,
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = viewModel(),
    authViewModel: com.nadyoga.fooddelivery.ui.screens.auth.AuthViewModel
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("ALL") }
    val favoriteRestaurants by favoritesViewModel.favoriteRestaurants.collectAsState()
    
    val filteredRestaurants = remember(restaurants, searchQuery, selectedFilter) {
        val filteredBySearch = if (searchQuery.isEmpty()) {
            restaurants
        } else {
            restaurants.filter { restaurant ->
                restaurant.name.contains(searchQuery, ignoreCase = true) ||
                restaurant.description.contains(searchQuery, ignoreCase = true)
            }
        }
        
        if (selectedFilter == "ALL") {
            filteredBySearch
        } else {
            filteredBySearch.filter { restaurant ->
                restaurant.type.name == selectedFilter
            }
        }
    }
    
    MainScaffold(
        navController = navController,
        authViewModel = authViewModel,
        title = "Food Delivery",
        actions = {
            IconButton(onClick = { /* TODO: Open search */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                placeholder = { Text("Search restaurants...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                singleLine = true
            )
            
            // Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    onClick = { selectedFilter = "ALL" },
                    selected = selectedFilter == "ALL",
                    label = { Text("All") }
                )
                FilterChip(
                    onClick = { selectedFilter = "PIZZA" },
                    selected = selectedFilter == "PIZZA",
                    label = { Text("🍕 Pizza") }
                )
                FilterChip(
                    onClick = { selectedFilter = "SUSHI" },
                    selected = selectedFilter == "SUSHI",
                    label = { Text("🍣 Sushi") }
                )
                FilterChip(
                    onClick = { selectedFilter = "BURGER" },
                    selected = selectedFilter == "BURGER",
                    label = { Text("🍔 Burger") }
                )
            }
            
            // Restaurant List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(filteredRestaurants) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onClick = { onRestaurantClick(restaurant) },
                        isFavorite = favoriteRestaurants.contains(restaurant.id),
                        onFavoriteClick = { favoritesViewModel.toggleFavorite(restaurant.id) }
                    )
                }
            }
        }
    }
}
