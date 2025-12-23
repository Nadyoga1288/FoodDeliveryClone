package com.nadyoga.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nadyoga.fooddelivery.ui.screens.restaurant_list.RestaurantListScreen
import com.nadyoga.fooddelivery.ui.theme.FoodDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryTheme {  // ← використовуємо твою тему з Dark Mode
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RestaurantListScreen()  // ← запускаємо головний екран зі списком ресторанів
                }
            }
        }
    }
}