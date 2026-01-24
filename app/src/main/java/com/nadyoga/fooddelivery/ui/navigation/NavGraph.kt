package com.nadyoga.fooddelivery.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import com.nadyoga.fooddelivery.data.repository.FoodRepository
import com.nadyoga.fooddelivery.ui.CartViewModel
import com.nadyoga.fooddelivery.ui.FavoritesViewModel
import com.nadyoga.fooddelivery.ui.screens.RestaurantListScreen
import com.nadyoga.fooddelivery.ui.screens.cart.CartScreen
import com.nadyoga.fooddelivery.ui.screens.menu.MenuScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val cartViewModel: CartViewModel = viewModel()
    val foodRepository = FoodRepository()
    val restaurants by produceState<List<Restaurant>>(initialValue = emptyList()) {
        foodRepository.getRestaurants().collect { value = it }
    }

    NavHost(
        navController = navController,
        startDestination = "restaurant_list"
    ) {
        composable("restaurant_list") {
            RestaurantListScreen(
                restaurants = restaurants,
                onRestaurantClick = { restaurant ->
                    navController.navigate("menu/${restaurant.type.name}")
                }
            )
        }

        composable(
            route = "menu/{restaurantType}",
            arguments = listOf(navArgument("restaurantType") { type = NavType.StringType })
        ) { backStackEntry ->
            val typeName = backStackEntry.arguments?.getString("restaurantType") ?: "PIZZA"
            val selectedType = RestaurantType.valueOf(typeName)

            MenuScreen(
                selectedType = selectedType,
                cartViewModel = cartViewModel,
                onBackClick = { navController.popBackStack() },
                onViewCartClick = { navController.navigate("cart") }
            )
        }

        composable("cart") {
            CartScreen(
                cartViewModel = cartViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
