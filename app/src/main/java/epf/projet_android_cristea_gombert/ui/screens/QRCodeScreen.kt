package epf.projet_android_cristea_gombert.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QRCodeScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
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