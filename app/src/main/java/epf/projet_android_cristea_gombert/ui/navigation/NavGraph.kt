package epf.projet_android_cristea_gombert.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import epf.projet_android_cristea_gombert.ui.screens.CartScreen
import epf.projet_android_cristea_gombert.ui.screens.HomeScreen
import epf.projet_android_cristea_gombert.ui.screens.ProductDetailScreen
import epf.projet_android_cristea_gombert.ui.screens.ProductListScreen
import epf.projet_android_cristea_gombert.ui.screens.ProductSearchScreen
import epf.projet_android_cristea_gombert.ui.viewmodel.CartViewModel
import epf.projet_android_cristea_gombert.ui.viewmodel.ProductViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Products : Screen("products")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
    object Search : Screen("search")
    object Cart : Screen("cart")

}

@Composable
fun NavGraph(navController: NavHostController) {
    val productViewModel: ProductViewModel = viewModel()
    val cartViewModel: CartViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen( onNavigateToProducts = {navController.navigate(Screen.Products.route)},
                        onNavigateToSearch = {navController.navigate(Screen.Search.route)}
            )
        }
        composable(Screen.Products.route) {
            ProductListScreen(
                viewModel = productViewModel,
                onNavigateHome = { navController.navigate(Screen.Home.route) },
                onNavigateToDetail = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        composable(
            Screen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            val product = productViewModel.products.firstOrNull { it.id == productId }

            product?.let {
                ProductDetailScreen(
                    product = it,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToCart = { navController.navigate(Screen.Cart.route) },
                    cartViewModel
                )
            }
        }
        composable(Screen.Search.route) {
            ProductSearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToDetail = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }
        composable(Screen.Cart.route) {
            CartScreen(onNavigateBack = { navController.popBackStack() },
            cartViewModel
            )
        }

    }
}
