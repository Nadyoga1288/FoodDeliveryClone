package com.nadyoga.fooddelivery.ui

import com.nadyoga.fooddelivery.data.api.model.CartItem
import com.nadyoga.fooddelivery.data.api.model.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartViewModelTest {

    private lateinit var viewModel: CartViewModel
    private lateinit var testMenuItem: MenuItem

    @Before
    fun setup() {
        viewModel = CartViewModel()
        testMenuItem = MenuItem(1, "Test Item", "10.00€", "🍕")
    }

    @Test
    fun `addItem should add item to empty cart`() {
        // When
        viewModel.addItem(testMenuItem)

        // Then
        assertEquals(1, viewModel.cartItems.size)
        assertEquals(1, viewModel.getTotalCount())
        assertEquals(10.0, viewModel.getTotalPrice())
    }

    @Test
    fun `addItem should increase quantity for existing item`() {
        // Given
        viewModel.addItem(testMenuItem)

        // When
        viewModel.addItem(testMenuItem)

        // Then
        assertEquals(1, viewModel.cartItems.size)
        assertEquals(2, viewModel.getTotalCount())
        assertEquals(20.0, viewModel.getTotalPrice())
    }

    @Test
    fun `removeItem should remove item from cart`() {
        // Given
        viewModel.addItem(testMenuItem)

        // When
        val cartItem = viewModel.cartItems.first()
        viewModel.removeItem(cartItem)

        // Then
        assertEquals(0, viewModel.cartItems.size)
        assertEquals(0, viewModel.getTotalCount())
        assertEquals(0.0, viewModel.getTotalPrice())
    }

    @Test
    fun `updateQuantity should update item quantity`() {
        // Given
        viewModel.addItem(testMenuItem)
        val cartItem = viewModel.cartItems.first()

        // When
        viewModel.updateQuantity(cartItem, 3)

        // Then
        assertEquals(1, viewModel.cartItems.size)
        assertEquals(3, viewModel.getTotalCount())
        assertEquals(30.0, viewModel.getTotalPrice())
    }

    @Test
    fun `updateQuantity with zero should remove item`() {
        // Given
        viewModel.addItem(testMenuItem)
        val cartItem = viewModel.cartItems.first()

        // When
        viewModel.updateQuantity(cartItem, 0)

        // Then
        assertEquals(0, viewModel.cartItems.size)
    }

    @Test
    fun `clearCart should remove all items`() {
        // Given
        viewModel.addItem(testMenuItem)
        viewModel.addItem(MenuItem(2, "Another Item", "5.00€", "🍔"))

        // When
        viewModel.clearCart()

        // Then
        assertEquals(0, viewModel.cartItems.size)
        assertEquals(0, viewModel.getTotalCount())
        assertEquals(0.0, viewModel.getTotalPrice())
    }

    @Test
    fun `getTotalPrice should handle comma decimal separator`() {
        // Given
        val itemWithComma = MenuItem(2, "Comma Item", "9,99€", "🍣")
        viewModel.addItem(itemWithComma)

        // When
        val totalPrice = viewModel.getTotalPrice()

        // Then
        assertEquals(9.99, totalPrice)
    }

    @Test
    fun `getTotalPrice should handle invalid price format`() {
        // Given
        val itemWithInvalidPrice = MenuItem(2, "Invalid Item", "invalid€", "🍔")
        viewModel.addItem(itemWithInvalidPrice)

        // When
        val totalPrice = viewModel.getTotalPrice()

        // Then
        assertEquals(0.0, totalPrice)
    }
}
