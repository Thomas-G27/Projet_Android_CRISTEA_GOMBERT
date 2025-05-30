package epf.projet_android_cristea_gombert.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import epf.projet_android_cristea_gombert.model.CartItem
import epf.projet_android_cristea_gombert.model.Product

class CartViewModel : ViewModel() {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> get() = _cartItems

    fun addToCart(product: Product) {
        // Cherche si le produit est déjà dans le panier
        val existingItem = _cartItems.find { it.product.id == product.id }

        if (existingItem != null) {
            // Incrémente la quantité si déjà présent
            existingItem.quantity++
        } else {
            // Ajoute un nouvel élément sinon
            _cartItems.add(CartItem(product, 1))
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
}

