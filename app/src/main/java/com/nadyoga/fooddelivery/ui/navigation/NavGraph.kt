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
import com.nadyoga.fooddelivery.ui.screens.auth.AuthViewModel
import com.nadyoga.fooddelivery.ui.screens.auth.LoginScreen
import com.nadyoga.fooddelivery.ui.screens.auth.RegisterScreen
import com.nadyoga.fooddelivery.ui.screens.cart.CartScreen
import com.nadyoga.fooddelivery.ui.screens.checkout.CheckoutScreen
import com.nadyoga.fooddelivery.ui.screens.menu.MenuScreen
import com.nadyoga.fooddelivery.ui.screens.orders.OrderHistoryScreen
import com.nadyoga.fooddelivery.ui.screens.profile.ProfileScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    val cartViewModel: CartViewModel = viewModel()
    val foodRepository = FoodRepository()
    val restaurants by produceState<List<Restaurant>>(initialValue = emptyList()) {
        foodRepository.getRestaurants().collect { value = it }
    }
    val authViewModel: AuthViewModel = viewModel()
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) "restaurant_list" else "login"
    ) {
        // Authentication Screens
        composable("login") {
            LoginScreen(
                onLoginSuccess = { 
                    navController.navigate("restaurant_list") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { 
                    navController.navigate("restaurant_list") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        // Main App Screens
        composable("restaurant_list") {
            RestaurantListScreen(
                restaurants = restaurants,
                onRestaurantClick = { restaurant ->
                    navController.navigate("menu/${restaurant.type.name}")
                },
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(
            route = "menu/{restaurantType}",
            arguments = listOf(navArgument("restaurantType") { type = NavType.StringType })
        ) { backStackEntry ->
            val restaurantType = backStackEntry.arguments?.getString("restaurantType")
            val type = RestaurantType.values().find { it.name == restaurantType } ?: RestaurantType.PIZZA
            
            MenuScreen(
                restaurantType = type,
                cartViewModel = cartViewModel,
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate("cart") }
            )
        }

        composable("cart") {
            CartScreen(
                cartViewModel = cartViewModel,
                onBackClick = { navController.popBackStack() },
                onCheckout = { 
                    navController.navigate("checkout")
                }
            )
        }
        
        composable("checkout") {
            CheckoutScreen(
                navController = navController,
                authViewModel = authViewModel,
                onCheckoutSuccess = {
                    navController.navigate("order_history") {
                        popUpTo("restaurant_list") { inclusive = false }
                    }
                }
            )
        }
        
        composable("profile") {
            ProfileScreen(
                authViewModel = authViewModel,
                navController = navController,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("restaurant_list") { inclusive = true }
                    }
                }
            )
        }
        
        composable("order_history") {
            OrderHistoryScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}
