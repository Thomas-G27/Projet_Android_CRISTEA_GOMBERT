package epf.projet_android_cristea_gombert.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import epf.projet_android_cristea_gombert.api.RetrofitInstance
import epf.projet_android_cristea_gombert.model.Product
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class ProductViewModel : ViewModel() {
    var products by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        viewModelScope.launch {
            try {
                products = RetrofitInstance.api.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}
