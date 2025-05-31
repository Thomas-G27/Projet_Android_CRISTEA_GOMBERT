package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import epf.projet_android_cristea_gombert.model.CartItem
import epf.projet_android_cristea_gombert.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToPayment: () -> Unit,
    cartViewModel: CartViewModel
) {
    val activeItems = cartViewModel.activeItems
    val setAsideItems = cartViewModel.setAsideItems
    val activeTotal = cartViewModel.activeTotal
    val setAsideTotal = cartViewModel.setAsideTotal

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (activeItems.isEmpty() && setAsideItems.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Votre panier est vide",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ajoutez des produits pour commencer vos achats",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onNavigateBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Retour aux produits")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                if (activeItems.isNotEmpty()) {
                    item {
                        Text(
                            text = "Panier actif",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    items(activeItems) { item ->
                        CartItemCard(
                            cartItem = item,
                            onClick = { onNavigateToDetail(item.product.id) },
                            onIncreaseQuantity = { cartViewModel.addToCart(item.product) },
                            onDecreaseQuantity = { cartViewModel.decreaseQuantity(item.product) },
                            onToggleSetAside = { cartViewModel.toggleSetAside(item.product) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

                if (setAsideItems.isNotEmpty()) {
                    item {
                        Divider(modifier = Modifier.padding(vertical = 16.dp))
                        Text(
                            text = "Mis de côté",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    
                    items(setAsideItems) { item ->
                        CartItemCard(
                            cartItem = item,
                            onClick = { onNavigateToDetail(item.product.id) },
                            onIncreaseQuantity = { cartViewModel.addToCart(item.product) },
                            onDecreaseQuantity = { cartViewModel.decreaseQuantity(item.product) },
                            onToggleSetAside = { cartViewModel.toggleSetAside(item.product) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            if (activeItems.isNotEmpty()) {
                // Total et bouton de paiement pour les articles actifs
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Total panier",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "%.2f €".format(activeTotal),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Button(
                            onClick = onNavigateToPayment,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text("Procéder au paiement")
                        }
                    }
                }
            }

            if (setAsideItems.isNotEmpty()) {
                // Total des articles mis de côté
                Text(
                    text = "Total mis de côté: %.2f €".format(setAsideTotal),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Boutons de navigation en bas
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onNavigateBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Retour")
                }

                Button(
                    onClick = { cartViewModel.clearCart() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text("Vider le panier")
                }
            }
        }
    }
}

@Composable
fun CartItemCard(
    cartItem: CartItem,
    onClick: () -> Unit,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onToggleSetAside: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = cartItem.product.image,
                    contentDescription = cartItem.product.title,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = cartItem.product.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "%.2f €".format(cartItem.product.price * cartItem.quantity),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Contrôles de quantité
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onDecreaseQuantity,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Text("-", style = MaterialTheme.typography.titleMedium)
                    }
                    
                    Text(
                        text = cartItem.quantity.toString(),
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    IconButton(
                        onClick = onIncreaseQuantity,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Text("+", style = MaterialTheme.typography.titleMedium)
                    }
                }
                
                // Bouton Mettre de côté
                TextButton(
                    onClick = onToggleSetAside,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(if (cartItem.isSetAside) "Remettre au panier" else "Mettre de côté")
                }
            }
        }
    }
}
