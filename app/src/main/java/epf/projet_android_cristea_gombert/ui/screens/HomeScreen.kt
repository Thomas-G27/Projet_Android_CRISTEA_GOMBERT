package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToProducts: () -> Unit,
    onNavigateToSearch: () -> Unit,
    onNavigateToQRCode: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Bienvenue dans notre boutique !")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToProducts) {
            Text("Voir les produits")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToSearch) {
            Text("Rechercher un produit")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToQRCode) {
            Text("recherche par QRCode")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onNavigateToCart) {
            Text("Mon panier")
        }
    }
}