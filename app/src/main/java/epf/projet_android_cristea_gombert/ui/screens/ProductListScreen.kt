package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import epf.projet_android_cristea_gombert.model.Product
import epf.projet_android_cristea_gombert.ui.viewmodel.ProductViewModel

@Composable
fun ProductListScreen(
    viewModel: ProductViewModel = viewModel(),
    onNavigateHome: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToCart: () -> Unit
) {
    val products = viewModel.products
    val isLoading = viewModel.isLoading

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onNavigateToDetail(product.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Button(
                onClick = onNavigateHome,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text("Retour")
            }
            Button(
                    onClick = onNavigateToCart,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
            ) {
                Text("Mon panier")
            }
        }
    }
}



@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${product.price} €", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}