package com.nadyoga.fooddelivery.ui.screens.orders

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nadyoga.fooddelivery.ui.screens.auth.AuthViewModel
import java.text.SimpleDateFormat
import java.util.*

data class Order(
    val id: String,
    val restaurantName: String,
    val items: List<OrderItem>,
    val totalPrice: String,
    val status: OrderStatus,
    val orderDate: Date,
    val deliveryAddress: String
)

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: String
)

enum class OrderStatus {
    PENDING,
    CONFIRMED,
    PREPARING,
    ON_THE_WAY,
    DELIVERED,
    CANCELLED
}

class OrderViewModel : androidx.lifecycle.ViewModel() {
    
    private val _orders = mutableStateListOf<Order>()
    val orders: List<Order> = _orders
    
    init {
        loadMockOrders()
    }
    
    private fun loadMockOrders() {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        
        _orders.addAll(
            listOf(
                Order(
                    id = "ORD001",
                    restaurantName = "Yami Sushi",
                    items = listOf(
                        OrderItem("California Roll", 2, "27.80€"),
                        OrderItem("Salmon Nigiri", 4, "50.00€")
                    ),
                    totalPrice = "77.80€",
                    status = OrderStatus.DELIVERED,
                    orderDate = dateFormat.parse("2024-01-20 18:30") ?: Date(),
                    deliveryAddress = "Helsinki, Finland"
                ),
                Order(
                    id = "ORD002",
                    restaurantName = "Burger King Aura",
                    items = listOf(
                        OrderItem("Classic Cheeseburger", 1, "12.90€"),
                        OrderItem("French Fries", 1, "4.90€")
                    ),
                    totalPrice = "17.80€",
                    status = OrderStatus.ON_THE_WAY,
                    orderDate = dateFormat.parse("2024-01-21 12:15") ?: Date(),
                    deliveryAddress = "Espoo, Finland"
                ),
                Order(
                    id = "ORD003",
                    restaurantName = "Pizzeria Antonio",
                    items = listOf(
                        OrderItem("Margherita", 1, "12.90€"),
                        OrderItem("Pepperoni", 1, "14.90€")
                    ),
                    totalPrice = "27.80€",
                    status = OrderStatus.PREPARING,
                    orderDate = dateFormat.parse("2024-01-21 19:45") ?: Date(),
                    deliveryAddress = "Helsinki, Finland"
                )
            )
        )
    }
    
    fun cancelOrder(orderId: String) {
        val orderIndex = _orders.indexOfFirst { it.id == orderId }
        if (orderIndex != -1) {
            _orders[orderIndex] = _orders[orderIndex].copy(status = OrderStatus.CANCELLED)
        }
    }
    
    fun repeatOrder(orderId: String) {
        // In real app, this would add items to cart
        // For demo, we'll just show a message
    }
}

@Composable
fun OrderHistoryScreen(
    orderViewModel: OrderViewModel = viewModel(),
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val orders = orderViewModel.orders
    val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Text(
                text = "Order History",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (orders.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "No Orders",
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No orders yet",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Start ordering to see your history here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(orders) { order ->
                    OrderCard(
                        order = order,
                        dateFormat = dateFormat,
                        onCancelOrder = { orderViewModel.cancelOrder(order.id) },
                        onRepeatOrder = { orderViewModel.repeatOrder(order.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun OrderCard(
    order: Order,
    dateFormat: SimpleDateFormat,
    onCancelOrder: () -> Unit,
    onRepeatOrder: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = order.restaurantName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Order #${order.id}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                OrderStatusChip(status = order.status)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Items
            order.items.take(3).forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${item.quantity}x ${item.name}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = item.price,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            if (order.items.size > 3) {
                Text(
                    text = "+${order.items.size - 3} more items",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Footer
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = dateFormat.format(order.orderDate),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = order.deliveryAddress,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                Text(
                    text = order.totalPrice,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Action buttons
            if (order.status != OrderStatus.CANCELLED && order.status != OrderStatus.DELIVERED) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = onCancelOrder,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }
                    
                    Button(
                        onClick = onRepeatOrder,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Repeat Order")
                    }
                }
            } else if (order.status == OrderStatus.DELIVERED) {
                Button(
                    onClick = onRepeatOrder,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Repeat Order")
                }
            }
        }
    }
}

@Composable
fun OrderStatusChip(status: OrderStatus) {
    val (text, color) = when (status) {
        OrderStatus.PENDING -> "Pending" to MaterialTheme.colorScheme.tertiary
        OrderStatus.CONFIRMED -> "Confirmed" to MaterialTheme.colorScheme.primary
        OrderStatus.PREPARING -> "Preparing" to MaterialTheme.colorScheme.secondary
        OrderStatus.ON_THE_WAY -> "On the way" to MaterialTheme.colorScheme.primary
        OrderStatus.DELIVERED -> "Delivered" to MaterialTheme.colorScheme.primary
        OrderStatus.CANCELLED -> "Cancelled" to MaterialTheme.colorScheme.error
    }
    
    Surface(
        shape = MaterialTheme.shapes.small,
        color = color.copy(alpha = 0.1f)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            fontSize = 12.sp,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}
