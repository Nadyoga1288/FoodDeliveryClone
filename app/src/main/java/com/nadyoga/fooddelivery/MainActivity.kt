package com.nadyoga.fooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nadyoga.fooddelivery.ui.navigation.NavGraph
import com.nadyoga.fooddelivery.ui.theme.FoodDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FoodDeliveryTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = navController)
                }
            }
        }
    }
}
