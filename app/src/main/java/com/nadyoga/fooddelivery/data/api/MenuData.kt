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
                    "https://images.unsplash.com/photo-1579584436535-c60bc7a5b84c?w=400&h=400&fit=crop",
                    "Crab, avocado, cucumber", "Maki"),
                MenuItem(8, "Philadelphia Roll", "15.90€", "🍣",
                    "https://images.unsplash.com/photo-1598455112583-2f9e8c8b1a2c?w=400&h=400&fit=crop",
                    "Salmon, cream cheese, avocado", "Maki"),
                MenuItem(9, "Salmon Nigiri", "12.50€", "🍣",
                    "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400&h=400&fit=crop",
                    "Fresh salmon over rice", "Nigiri"),
                MenuItem(10, "Tuna Sashimi", "16.90€", "🍣",
                    "https://images.unsplash.com/photo-1617196034766-5d6b4cd0c2e2?w=400&h=400&fit=crop",
                    "Premium tuna slices", "Sashimi"),
                MenuItem(11, "Dragon Roll", "18.90€", "🍣",
                    "https://images.unsplash.com/photo-1598894137054-3fe2248c5fd1?w=400&h=400&fit=crop",
                    "Eel, cucumber, avocado topping", "Special"),
                MenuItem(12, "Tempura Uramaki", "14.90€", "🍣",
                    "https://images.unsplash.com/photo-1584168610846-a9c432b4a2f2?w=400&h=400&fit=crop",
                    "Tempura shrimp, avocado", "Uramaki"),
                MenuItem(13, "Miso Soup", "4.90€", "🍣",
                    "https://images.unsplash.com/photo-1606495909903-584c4c5d5d9c?w=400&h=400&fit=crop",
                    "Traditional soybean paste soup", "Soup"),
                MenuItem(14, "Edamame", "5.90€", "🍣",
                    "https://images.unsplash.com/photo-1553621045-f92e00ea2e81?w=400&h=400&fit=crop",
                    "Steamed soybeans with sea salt", "Appetizer")
            )
            RestaurantType.BURGER -> listOf(
                MenuItem(15, "Classic Cheeseburger", "12.90€", "🍔",
                    "https://images.unsplash.com/photo-1568901346375-23c9458c6966?w=400&h=400&fit=crop",
                    "Beef patty, cheese, lettuce, tomato", "Classic"),
                MenuItem(16, "Bacon Burger", "14.90€", "🍔",
                    "https://images.unsplash.com/photo-1563612456674-4c7a1a015c7d?w=400&h=400&fit=crop",
                    "Beef, crispy bacon, cheddar cheese", "Premium"),
                MenuItem(17, "Veggie Burger", "13.90€", "🍔",
                    "https://images.unsplash.com/photo-1586190848861-99aa4a171e90?w=400&h=400&fit=crop",
                    "Plant-based patty, vegetables", "Vegetarian"),
                MenuItem(18, "Double Cheeseburger", "16.90€", "🍔",
                    "https://images.unsplash.com/photo-1553979459-2221cb1b092e?w=400&h=400&fit=crop",
                    "Double beef, double cheese", "Large"),
                MenuItem(19, "Chicken Burger", "11.90€", "🍔",
                    "https://images.unsplash.com/photo-1562967916-eb82221dfb92?w=400&h=400&fit=crop",
                    "Crispy chicken, lettuce, mayo", "Chicken"),
                MenuItem(20, "BBQ Bacon Burger", "15.90€", "🍔",
                    "https://images.unsplash.com/photo-1550547660-9a0b5d985d71?w=400&h=400&fit=crop",
                    "BBQ sauce, bacon, onion rings", "BBQ"),
                MenuItem(21, "French Fries", "4.90€", "🍔",
                    "https://images.unsplash.com/photo-1576107232684-1279f390859f?w=400&h=400&fit=crop",
                    "Crispy golden fries with sea salt", "Side"),
                MenuItem(22, "Onion Rings", "5.90€", "🍔",
                    "https://images.unsplash.com/photo-1529056627948-a5eb5c9b7d8a?w=400&h=400&fit=crop",
                    "Breaded and fried onion rings", "Side")
            )
        }
    }
}
