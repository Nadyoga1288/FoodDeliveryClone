package com.nadyoga.fooddelivery.ui.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadyoga.fooddelivery.ui.CartViewModel
import java.util.Locale

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit
) {
    val cartItems = cartViewModel.cartItems
    val totalPrice = cartViewModel.getTotalPrice()

    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClick) { Text("←", fontSize = 24.sp) }
                    Text("Кошик", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                Button(
                    onClick = { /* Оплата */ },
                    modifier = Modifier.fillMaxWidth().padding(16.dp).height(56.dp)
                ) {
                    Text("Замовити • ${String.format(Locale.ROOT, "%.2f", totalPrice)}€")
                }
            }
        }
    ) { padding ->
        if (cartItems.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Кошик порожній 🛒")
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                items(cartItems) { item ->
                    val itemTotal = (item.menuItem.price.replace("€", "").toDoubleOrNull() ?: 0.0) * item.quantity

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item.menuItem.icon, fontSize = 24.sp)
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.menuItem.name, fontWeight = FontWeight.Bold)
                            Text("Кількість: ${item.quantity}", fontSize = 14.sp, color = Color.Gray)
                        }
                        Text("${String.format(Locale.ROOT, "%.2f", itemTotal)}€", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { cartViewModel.removeItem(item) }) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Red.copy(alpha = 0.6f))
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}
