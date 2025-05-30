package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import epf.projet_android_cristea_gombert.ui.viewmodel.ProductViewModel

@Composable
fun ProductSearchScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val allProducts = viewModel.products

    val filteredProducts = allProducts.filter {
        it.title.contains(query, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Rechercher un produit") },
                modifier = Modifier
                    .height(52.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(filteredProducts) { product ->
                    ProductCard(product = product, onClick = { onNavigateToDetail(product.id) })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text("Retour")
        }
    }
}
