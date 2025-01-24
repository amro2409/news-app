package com.alkamali.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.alkamali.newsapp.presentation.screens.DetailsItemScreen
import com.alkamali.newsapp.presentation.screens.home.HomeScreen
import com.alkamali.newsapp.presentation.screens.onboarding.OnBoardingScreen
import com.alkamali.newsapp.presentation.screens.splash.SplashScreen

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
            OnBoardingScreen(navController)
        }
        composable(route = Destinations.Home.route) {
            HomeScreen(navHostController = navController)
        }
        composable(
            route = Destinations.Details.route,
            arguments = listOf(navArgument(Destinations.Details.argItem) { type = NavType.IntType })
        ) {
            DetailsItemScreen(it.arguments?.getInt(Destinations.Details.argItem))
        }
        composable(route = Destinations.Search.route) {

        }

    }
}