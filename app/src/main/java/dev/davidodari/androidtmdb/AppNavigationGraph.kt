package dev.davidodari.androidtmdb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreen
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsScreenIntent
import dev.davidodari.androidtmdb.features.movies.MoviesScreen
import dev.davidodari.androidtmdb.features.movies.MoviesViewModel
import dev.davidodari.androidtmdb.features.movie_details.MovieDetailsViewModel
import dev.davidodari.androidtmdb.features.movies.MoviesScreenIntent

@Composable
fun AppNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Destinations.MOVIES.route, modifier) {
        composable(Destinations.MOVIES.route) {
            val viewModel = hiltViewModel<MoviesViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                MoviesScreen(
                    state = state,
                    onMovieSelected = { movie ->
                        // TODO Have navigation intent
                        navController.navigate("${Destinations.MOVIE_DETAILS.route}/${movie.id}")
                    },
                    onErrorActionClicked = {
                        viewModel.processIntent(MoviesScreenIntent.LoadLatestMovies)
                    },
                    onLoadMore = {
                        viewModel.processIntent(MoviesScreenIntent.LoadLatestMovies)
                    },
                    onSearch = { query ->
                        viewModel.processIntent(MoviesScreenIntent.SearchMovies(query))
                    },
                    onSearchActionClicked = {
                        viewModel.processIntent(MoviesScreenIntent.DisplaySearchScreen)
                    }
                )
            }
        }
        composable(Destinations.MOVIE_DETAILS.route + "/{movieId}") { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getString("movieId")?.toInt()
            requireNotNull(movieId)

            val viewModel = hiltViewModel<MovieDetailsViewModel>()

            viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId))

            viewModel.state.collectAsState().value.let { state ->
                MovieDetailsScreen(
                    state = state,
                    onErrorAction = {
                        viewModel.processIntent(MovieDetailsScreenIntent.LoadMovieDetail(movieId))
                    },
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

// TODO refactor to use sealed class and support arguments
enum class Destinations(val route: String) {
    MOVIES("movies"),
    MOVIE_DETAILS("movie_details")
}
