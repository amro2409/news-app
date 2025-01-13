package com.alkamali.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alkamali.newsapp.presentation.screens.DetailsHeroScreen
import com.alkamali.newsapp.presentation.screens.HomeScreen
import com.alkamali.newsapp.presentation.screens.OnBoardingScreen
import com.alkamali.newsapp.presentation.screens.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash.route
    ) {

        composable(route = Destinations.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Destinations.Welcome.route) {
            OnBoardingScreen()
        }
        composable(route = Destinations.Home.route) {
            HomeScreen()
        }
        composable(
            route = Destinations.Details.route,
            arguments = listOf(navArgument(Destinations.Details.argHero)
            { type = NavType.IntType })
        ) {
            DetailsHeroScreen(it.arguments?.getInt(Destinations.Details.argHero))
        }
        composable(route = Destinations.Search.route) {

        }

    }
}