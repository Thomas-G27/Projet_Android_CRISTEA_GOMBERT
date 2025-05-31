package epf.projet_android_cristea_gombert.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import epf.projet_android_cristea_gombert.api.RetrofitInstance
import epf.projet_android_cristea_gombert.model.Product
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class ProductViewModel : ViewModel() {
    private val _products = mutableStateOf<List<Product>>(emptyList())
    val products: List<Product> get() = _products.value

    private val _isLoading = mutableStateOf(true)
    val isLoading: Boolean get() = _isLoading.value

    private val _error = mutableStateOf<String?>(null)
    val error: String? get() = _error.value

    private val _searchQuery = mutableStateOf("")
    val searchQuery: String get() = _searchQuery.value

    private val _selectedCategory = mutableStateOf<String?>(null)
    val selectedCategory: String? get() = _selectedCategory.value

    private var allProducts = listOf<Product>()
    
    val categories = mutableStateListOf<String>()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                allProducts = RetrofitInstance.api.getProducts()
                // Extraire les catégories uniques
                categories.clear()
                categories.addAll(allProducts.map { it.category }.distinct().sorted())
                filterProducts()
            } catch (e: Exception) {
                _error.value = "Erreur lors du chargement des produits: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterProducts()
    }

    fun updateSelectedCategory(category: String?) {
        _selectedCategory.value = category
        filterProducts()
    }

    private fun filterProducts() {
        _products.value = allProducts.filter { product ->
            val matchesSearch = product.title.contains(_searchQuery.value, ignoreCase = true)
            val matchesCategory = _selectedCategory.value == null || product.category == _selectedCategory.value
            matchesSearch && matchesCategory
        }
    }

    fun retry() {
        loadProducts()
    }
}
