package epf.projet_android_cristea_gombert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import epf.projet_android_cristea_gombert.ui.screens.HomeScreen
import epf.projet_android_cristea_gombert.ui.screens.ProductListScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Products : Screen("products")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onNavigateToProducts = {
                navController.navigate(Screen.Products.route)
            })
        }
        composable(Screen.Products.route) {
            ProductListScreen(onNavigateHome = {
                navController.navigate(Screen.Home.route)
            })
        }
    }
}
