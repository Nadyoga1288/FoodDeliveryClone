package com.nadyoga.fooddelivery.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class User(
    val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String = "",
    val addresses: List<String> = emptyList()
)

class AuthViewModel : ViewModel() {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()
    
    init {
        // Check if user is already logged in (in real app, check SharedPreferences/secure storage)
        checkAuthStatus()
    }
    
    private fun checkAuthStatus() {
        // Simulate checking stored auth state
        // In real app, this would check secure storage or token validation
        viewModelScope.launch {
            // For demo, we'll start with no user logged in
            _isAuthenticated.value = false
            _currentUser.value = null
        }
    }
    
    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // Simulate network delay
                delay(1500)
                
                // Simulate login validation (in real app, call API)
                if (email.isNotBlank() && password.isNotBlank()) {
                    // Create mock user
                    val user = User(
                        id = "user_${System.currentTimeMillis()}",
                        name = email.split("@").firstOrNull()?.replace(".", " ")?.capitalize() ?: "User",
                        email = email,
                        phoneNumber = "+358 40 123 4567", // Finnish phone format
                        addresses = listOf(
                            "Helsinki, Finland",
                            "Espoo, Finland"
                        )
                    )
                    
                    _currentUser.value = user
                    _isAuthenticated.value = true
                    
                    // In real app, save token securely
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
    
    fun register(name: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // Simulate network delay
                delay(1500)
                
                // Simulate registration validation (in real app, call API)
                if (name.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                    // Create new user
                    val user = User(
                        id = "user_${System.currentTimeMillis()}",
                        name = name,
                        email = email,
                        phoneNumber = "+358 40 123 4567", // Finnish phone format
                        addresses = listOf(
                            "Helsinki, Finland"
                        )
                    )
                    
                    _currentUser.value = user
                    _isAuthenticated.value = true
                    
                    // In real app, save token securely
                    onResult(true)
                } else {
                    onResult(false)
                }
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            _currentUser.value = null
            _isAuthenticated.value = false
            
            // In real app, clear stored token
        }
    }
    
    fun updateProfile(user: User) {
        viewModelScope.launch {
            _currentUser.value = user
            // In real app, update via API
        }
    }
    
    fun addAddress(address: String) {
        viewModelScope.launch {
            val currentUser = _currentUser.value ?: return@launch
            val updatedAddresses = currentUser.addresses.toMutableList()
            updatedAddresses.add(address)
            
            _currentUser.value = currentUser.copy(addresses = updatedAddresses)
            // In real app, update via API
        }
    }
    
    fun removeAddress(address: String) {
        viewModelScope.launch {
            val currentUser = _currentUser.value ?: return@launch
            val updatedAddresses = currentUser.addresses.toMutableList()
            updatedAddresses.remove(address)
            
            _currentUser.value = currentUser.copy(addresses = updatedAddresses)
            // In real app, update via API
        }
    }
}
