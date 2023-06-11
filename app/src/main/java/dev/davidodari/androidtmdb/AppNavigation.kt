package dev.davidodari.androidtmdb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreen
import dev.davidodari.androidtmdb.features.movies.MoviesScreen
import dev.davidodari.androidtmdb.features.movies.MoviesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsViewModel

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destinations.MOVIES.route) {
        composable(Destinations.MOVIES.route) {
            val viewModel = hiltViewModel<MoviesViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                MoviesScreen(state) { movie ->
                    // TODO: Handle navigation
                }
            }
        }
        composable(Destinations.MOVIE_DETAILS.route) {
            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                MovieDetailsScreen(state)
            }
        }
    }
}

enum class Destinations(val route: String) {
    MOVIES("movies"),
    MOVIE_DETAILS("movie_details")
}
