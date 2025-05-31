package epf.projet_android_cristea_gombert.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import epf.projet_android_cristea_gombert.model.CartItem
import epf.projet_android_cristea_gombert.model.Product

class CartViewModel : ViewModel() {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems
    
    val activeItems: List<CartItem> get() = _cartItems.filter { !it.isSetAside }
    val setAsideItems: List<CartItem> get() = _cartItems.filter { it.isSetAside }
    
    val activeTotal: Double get() = activeItems.sumOf { it.product.price * it.quantity }
    val setAsideTotal: Double get() = setAsideItems.sumOf { it.product.price * it.quantity }

    fun getProductQuantity(product: Product): Int {
        return _cartItems.find { it.product.id == product.id }?.quantity ?: 0
    }

    fun addToCart(product: Product) {
        val existingItem = _cartItems.find { it.product.id == product.id }

        if (existingItem != null) {
            // On crée un nouvel objet CartItem avec la quantité incrémentée
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            val index = _cartItems.indexOf(existingItem)
            _cartItems[index] = updatedItem
        } else {
            _cartItems.add(CartItem(product, 1))
        }
    }

    fun decreaseQuantity(product: Product) {
        val existingItem = _cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                // On crée un nouvel objet CartItem avec la quantité décrémentée
                val updatedItem = existingItem.copy(quantity = existingItem.quantity - 1)
                val index = _cartItems.indexOf(existingItem)
                _cartItems[index] = updatedItem
            } else {
                _cartItems.remove(existingItem)
            }
        }
    }

    fun removeFromCart(product: Product) {
        val existingItem = _cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            _cartItems.remove(existingItem)
        }
    }

    fun clearCart() {
        _cartItems.clear()
    }
    
    fun toggleSetAside(product: Product) {
        val existingItem = _cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            val index = _cartItems.indexOf(existingItem)
            _cartItems[index] = existingItem.copy(isSetAside = !existingItem.isSetAside)
        }
    }
    
    fun clearSetAsideItems() {
        _cartItems.removeAll { it.isSetAside }
    }
}

