package com.nadyoga.fooddelivery.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadyoga.fooddelivery.data.api.model.Restaurant
import com.nadyoga.fooddelivery.data.api.model.RestaurantType

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Іконка по центру картки (велика, ідеально вирівняна)
            Box(
                modifier = Modifier
                    .size(100.dp)                    // розмір квадрата для іконки
                    .align(Alignment.CenterVertically), // центрування по вертикалі
                contentAlignment = Alignment.Center     // центрування емодзі всередині Box
            ) {
                Text(
                    text = when (restaurant.type) {
                        RestaurantType.PIZZA -> "🍕"
                        RestaurantType.SUSHI -> "🍣"
                        RestaurantType.BURGER -> "🍔"
                    },
                    fontSize = 70.sp,                // дуже великий і гарний розмір
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = restaurant.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}