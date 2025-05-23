package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import epf.projet_android_cristea_gombert.ui.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(viewModel: ProductViewModel = viewModel()) {
    val products = viewModel.products
    val isLoading = viewModel.isLoading

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(products) { product ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = "${product.price} €")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
