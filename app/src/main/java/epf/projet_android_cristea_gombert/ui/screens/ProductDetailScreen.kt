package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    val cartItems by remember { derivedStateOf { cartViewModel.cartItems } }
    val quantity = cartItems.find { it.product.id == product.id }?.quantity ?: 0

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

            // Affichage conditionnel de "Dans mon panier"
            if (quantity > 0) {
                Text(
                    text = "Dans mon panier",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Affichage de la quantité et boutons de gestion
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (quantity > 0) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { cartViewModel.decreaseQuantity(product) }
                        ) {
                            Text("-", style = MaterialTheme.typography.titleLarge)
                        }
                        
                        Text(
                            text = quantity.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        IconButton(
                            onClick = { cartViewModel.addToCart(product) }
                        ) {
                            Text("+", style = MaterialTheme.typography.titleLarge)
                        }
                    }
                } else {
                    Button(onClick = { cartViewModel.addToCart(product) }) {
                        Text("Ajouter au panier")
                    }
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