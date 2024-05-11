package com.rahul.themoviedb.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.themoviedb.features.details.MovieDetails
import com.rahul.themoviedb.features.list.MoviesList

object NavDestinations {
    const val MOVIE_LIST_ROUTE = "movieList"
    const val MOVIE_DETAILS_ROUTE = "movieDetails"
    const val MOVIE_ID_KEY = "movieId"
}

@Composable
fun NavGraph(startDestination: String = NavDestinations.MOVIE_LIST_ROUTE) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = startDestination) {
        composable(NavDestinations.MOVIE_LIST_ROUTE) {
            MoviesList(navController)
        }
        composable("${NavDestinations.MOVIE_DETAILS_ROUTE}/{${NavDestinations.MOVIE_ID_KEY}}") { backStackEntry ->
            val arguments = backStackEntry.arguments
            val movieId = arguments?.getString(NavDestinations.MOVIE_ID_KEY)?.toInt() ?: 0
            MovieDetails(movieId, navController)
        }
    }
}