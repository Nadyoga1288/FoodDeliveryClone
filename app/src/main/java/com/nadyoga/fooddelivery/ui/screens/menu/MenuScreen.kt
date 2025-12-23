package com.nadyoga.fooddelivery.ui.screens.menu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nadyoga.fooddelivery.data.api.getMenuFor
import com.nadyoga.fooddelivery.data.api.model.RestaurantType

@Composable
fun MenuScreen(
    selectedType: RestaurantType,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),  // трохи більше відступів для краси
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center  // ← головне: центрує ВСІ елементи вертикально
    ) {
        // Заголовок (центрований)
        Text(
            text = "Меню для ${selectedType.javaClass.simpleName}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Кнопка "Назад" (широка, центрована)
        Button(
            onClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth(0.8f)  // 80% ширини екрана — виглядає краще
                .padding(bottom = 40.dp)
        ) {
            Text("Назад до ресторанів")
        }

        // Список страв (центрований, з відступами)
        val menuItems = getMenuFor(selectedType)

        LazyColumn(
            modifier = Modifier.fillMaxWidth(0.9f),  // трохи вужчий список для краси
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(menuItems) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = item.icon,
                            fontSize = 48.sp,  // великий емодзі
                            modifier = Modifier.padding(end = 16.dp)
                        )

                        Column {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = item.price,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}