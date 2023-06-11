package dev.davidodari.androidtmdb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.davidodari.androidtmdb.data.movies.remote.utils.RequestParametersProvider

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {

    @Provides
    fun provideRequestParametersProvider(): RequestParametersProvider {
        return RequestParametersProvider()
    }
}
