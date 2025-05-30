package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import epf.projet_android_cristea_gombert.model.Product
import epf.projet_android_cristea_gombert.ui.viewmodel.CartViewModel

@Composable
fun ProductDetailScreen(
    product: Product,
    onNavigateBack: () -> Unit,
    onNavigateToCart: () -> Unit,
    cartViewModel: CartViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "${product.price} €", style = MaterialTheme.typography.titleMedium)
            Text(text = product.category, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.description, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                cartViewModel.addToCart(product)
            }) {
                Text("Ajouter au panier")
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
