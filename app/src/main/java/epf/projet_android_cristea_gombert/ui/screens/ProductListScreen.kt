package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import epf.projet_android_cristea_gombert.ui.viewmodel.ProductViewModel
import epf.projet_android_cristea_gombert.data.model.Product


@Composable
fun ProductListScreen(productViewModel: ProductViewModel) {
    val products = productViewModel.products.collectAsState()

    LazyColumn {
        items(products.value) { product ->
            Text(text = "${product.title} - ${product.price} €")
            Divider()
        }
    }
}
