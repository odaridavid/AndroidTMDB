package dev.davidodari.androidtmdb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreen
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreenIntent
import dev.davidodari.androidtmdb.features.movies.MoviesScreen
import dev.davidodari.androidtmdb.features.movies.MoviesViewModel
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsViewModel

@Composable
fun AppNavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destinations.MOVIES.route) {
        composable(Destinations.MOVIES.route) {
            val viewModel = hiltViewModel<MoviesViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                MoviesScreen(state) { movie ->
                    navController.navigate("${Destinations.MOVIE_DETAILS.route}/${movie.id}")
                }
            }
        }
        composable(Destinations.MOVIE_DETAILS.route + "/{movieId}") { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getString("movieId")?.toInt()
            requireNotNull(movieId)

            val viewModel = hiltViewModel<MovieDetailsViewModel>()
            viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId))

            viewModel.state.collectAsState().value.let { state ->
                MovieDetailsScreen(state)
            }
        }
    }
}

// TODO refactor to use sealed class and support arguments
enum class Destinations(val route: String) {
    MOVIES("movies"),
    MOVIE_DETAILS("movie_details")
}
