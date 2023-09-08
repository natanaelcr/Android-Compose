package com.example.firstcomposeapp.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.Routes


@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.HomeGrid.route) {
            HomeGrid(navController = navController)
        }

//        composable("${Routes.Detail.route}/{itemId}") { backStackEntry ->
//            val arguments = requireNotNull(backStackEntry.arguments)
//            val itemId = arguments.getString("itemId")?.toIntOrNull() ?: 0
//
//            Detail(navController = navController, itemId = itemId)
//        }

        composable(Routes.HomeView.route) {
            HomeView(navController = navController)
        }

        composable("${Routes.Details.route}/{movieId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val movieId = arguments.getString("movieId")?.toIntOrNull() ?: 0

            Details(navController = navController, movieId = movieId)
        }
    }
}
