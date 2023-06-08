package dev.davidodari.androidtmdb.di

import dev.davidodari.androidtmdb.data.movies.DefaultMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.davidodari.androidtmdb.core.api.MovieRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(weatherRepository: DefaultMoviesRepository): MovieRepository

}
