package com.alkamali.newsapp.navigation

sealed class Destinations(val route: String) {
    data object Splash: Destinations("splash_screen")
    data object Welcome: Destinations("welcome_screen")
    data object Home: Destinations("home_screen")
    data object Details: Destinations("details_screen/{itemId}"){
        const val argItem = "itemId"
        fun passItemId(itemId: Int): String{
            return "details_screen/$itemId"
        }
    }
    data object Search: Destinations("search_screen")
}