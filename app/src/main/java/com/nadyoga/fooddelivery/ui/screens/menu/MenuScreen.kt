package com.nadyoga.fooddelivery.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadyoga.fooddelivery.data.api.MenuData
import com.nadyoga.fooddelivery.data.api.model.RestaurantType
import com.nadyoga.fooddelivery.ui.CartViewModel
import com.nadyoga.fooddelivery.ui.components.MenuItemCard
import java.util.Locale

@Composable
fun MenuScreen(
    selectedType: RestaurantType,
    cartViewModel: CartViewModel,
    onBackClick: () -> Unit,
    onViewCartClick: () -> Unit
) {
    // Отримуємо список страв для обраного типу ресторану
    val menuItems = MenuData.getMenuItems(selectedType)

    // Дані з ViewModel для нижньої панелі
    val cartItems = cartViewModel.cartItems
    val totalPrice = cartViewModel.getTotalPrice()
    val totalCount = cartViewModel.getTotalCount() // Загальна кількість одиниць

    Scaffold(
        bottomBar = {
            // Кнопка з'являється лише якщо в кошику щось є
            if (cartItems.isNotEmpty()) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shadowElevation = 8.dp,
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Button(
                        onClick = onViewCartClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .height(56.dp),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Переглянути кошик",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            // Відображаємо загальну кількість та суму
                            Text(
                                text = "($totalCount) • ${String.format(Locale.ROOT, "%.2f", totalPrice)}€",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 16.dp)
        ) {
            // Кнопка назад
            TextButton(
                onClick = onBackClick,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text("← Назад до ресторанів", fontSize = 16.sp)
            }

            // Заголовок меню
            Text(
                text = "Меню: ${selectedType.name}",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Список страв
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(menuItems) { item ->
                    MenuItemCard(
                        menuItem = item,
                        onAddClick = {
                            // Додаємо страву через ViewModel
                            cartViewModel.addItem(item)
                        }
                    )
                }
            }
        }
    }
}
