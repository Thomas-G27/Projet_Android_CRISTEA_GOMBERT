package epf.projet_android_cristea_gombert.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import epf.projet_android_cristea_gombert.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    cartViewModel: CartViewModel
) {
    val cartItems = cartViewModel.cartItems

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mon panier", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(cartItems) { cartItems ->
                Row (
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)){

                    Text(
                        text = "${cartItems.product.title} - ${cartItems.product.price} €",
                        modifier = Modifier.weight(0.8f)
                    )
                    Text(
                        text = "Quantity : ${cartItems.quantity}",
                        modifier = Modifier.weight(0.2f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Retour")
        }
    }
}
