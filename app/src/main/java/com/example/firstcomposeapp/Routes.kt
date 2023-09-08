package com.example.firstcomposeapp


sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object HomeGrid : Routes("HomeGrid")
    object Detail : Routes("Detail")
    object HomeView : Routes("HomePage")
    object Details : Routes("Details")

}