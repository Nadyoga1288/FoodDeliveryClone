package com.nadyoga.fooddelivery.data.api

import com.nadyoga.fooddelivery.data.api.model.MenuItem
import com.nadyoga.fooddelivery.data.api.model.RestaurantType

object MenuData {
    fun getMenuItems(type: RestaurantType): List<MenuItem> {
        return when (type) {
            RestaurantType.PIZZA -> listOf(
                MenuItem(1, "Margherita", "12.90€", "🍕", 
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop",
                    "Fresh tomatoes, mozzarella, basil", "Classic"),
                MenuItem(2, "Pepperoni", "14.90€", "🍕",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop&auto=format",
                    "Spicy pepperoni, mozzarella", "Popular"),
                MenuItem(3, "Quattro Stagioni", "16.50€", "🍕",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop&q=80",
                    "Four seasons: ham, mushrooms, artichokes, olives", "Specialty"),
                MenuItem(4, "Frutti di Mare", "18.90€", "🍕",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop&auto=format&q=80",
                    "Fresh seafood: shrimp, mussels, calamari", "Seafood"),
                MenuItem(5, "Truffle Mushroom", "19.90€", "🍕",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop&q=80&auto=format",
                    "Truffle oil, mixed mushrooms, mozzarella", "Premium"),
                MenuItem(6, "Veggie Supreme", "15.90€", "🍕",
                    "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=300&h=300&fit=crop&auto=format&q=80",
                    "Bell peppers, zucchini, eggplant, olives", "Vegetarian")
            )
            RestaurantType.SUSHI -> listOf(
                MenuItem(7, "California Roll", "13.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop",
                    "Crab, avocado, cucumber", "Maki"),
                MenuItem(8, "Philadelphia Roll", "15.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&auto=format",
                    "Salmon, cream cheese, avocado", "Maki"),
                MenuItem(9, "Salmon Nigiri", "12.50€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&q=80",
                    "Fresh salmon over rice", "Nigiri"),
                MenuItem(10, "Tuna Sashimi", "16.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&auto=format&q=80",
                    "Premium tuna slices", "Sashimi"),
                MenuItem(11, "Dragon Roll", "18.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&q=80&auto=format",
                    "Eel, cucumber, avocado topping", "Special"),
                MenuItem(12, "Tempura Uramaki", "14.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&auto=format&q=80",
                    "Tempura shrimp, avocado", "Uramaki"),
                MenuItem(13, "Miso Soup", "4.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&q=80&auto=format",
                    "Traditional soybean paste soup", "Soup"),
                MenuItem(14, "Edamame", "5.90€", "🍣",
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=300&h=300&fit=crop&auto=format&q=80",
                    "Steamed soybeans with sea salt", "Appetizer")
            )
            RestaurantType.BURGER -> listOf(
                MenuItem(15, "Classic Cheeseburger", "12.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop",
                    "Beef patty, cheese, lettuce, tomato", "Classic"),
                MenuItem(16, "Bacon Burger", "14.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&auto=format",
                    "Beef, crispy bacon, cheddar cheese", "Premium"),
                MenuItem(17, "Veggie Burger", "13.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&q=80",
                    "Plant-based patty, vegetables", "Vegetarian"),
                MenuItem(18, "Double Cheeseburger", "16.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&auto=format&q=80",
                    "Double beef, double cheese", "Large"),
                MenuItem(19, "Chicken Burger", "11.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&q=80&auto=format",
                    "Crispy chicken, lettuce, mayo", "Chicken"),
                MenuItem(20, "BBQ Bacon Burger", "15.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&auto=format&q=80",
                    "BBQ sauce, bacon, onion rings", "BBQ"),
                MenuItem(21, "French Fries", "4.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&auto=format&q=80",
                    "Crispy golden fries with sea salt", "Side"),
                MenuItem(22, "Onion Rings", "5.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=300&h=300&fit=crop&auto=format&q=80",
                    "Breaded and fried onion rings", "Side")
            )
        }
    }
}
