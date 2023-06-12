package dev.davidodari.androidtmdb.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.davidodari.androidtmdb.core.usecases.DefaultGetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.core.usecases.DefaultGetLatestMovieListUseCase
import dev.davidodari.androidtmdb.core.usecases.DefaultSearchMoviesByTitleUseCase
import dev.davidodari.androidtmdb.core.usecases.GetLatestMovieDetailsUseCase
import dev.davidodari.androidtmdb.core.usecases.GetLatestMoviesListUseCase
import dev.davidodari.androidtmdb.core.usecases.SearchMoviesByTitleUseCase

@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindGetLatestMovieDetailsUseCase(
        getLatestMovieDetails: DefaultGetLatestMovieDetailsUseCase
    ): GetLatestMovieDetailsUseCase

    @Binds
    fun bindGetLatestMoviesListUseCase(
        getLatestMoviesList: DefaultGetLatestMovieListUseCase
    ): GetLatestMoviesListUseCase

    @Binds
    fun bindSearchMoviesByTitleUseCase(
        searchMoviesByTitle: DefaultSearchMoviesByTitleUseCase
    ): SearchMoviesByTitleUseCase
}
