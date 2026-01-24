package com.nadyoga.fooddelivery.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nadyoga.fooddelivery.data.api.model.CartItem
import com.nadyoga.fooddelivery.data.api.model.MenuItem

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addItem(menuItem: MenuItem) {
        val existingItem = _cartItems.find { it.menuItem.id == menuItem.id }

        if (existingItem != null) {
            val index = _cartItems.indexOf(existingItem)
            _cartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
        } else {
            _cartItems.add(CartItem(menuItem = menuItem, quantity = 1))
        }
    }

    fun removeItem(cartItem: CartItem) {
        _cartItems.remove(cartItem)
    }

    fun updateQuantity(cartItem: CartItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            _cartItems.remove(cartItem)
        } else {
            val index = _cartItems.indexOf(cartItem)
            if (index >= 0) {
                _cartItems[index] = cartItem.copy(quantity = newQuantity)
            }
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { item ->
            val priceValue = item.menuItem.price.replace("€", "").replace(",", ".").toDoubleOrNull() ?: 0.0
            priceValue * item.quantity
        }
    }

    fun getTotalCount(): Int {
        return _cartItems.sumOf { it.quantity }
    }
}
