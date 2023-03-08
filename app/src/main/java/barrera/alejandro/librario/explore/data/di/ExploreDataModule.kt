package barrera.alejandro.librario.explore.data.di

import barrera.alejandro.librario.explore.data.api.GoogleBooksApi
import barrera.alejandro.librario.explore.data.repository.ExploreRepositoryImpl
import barrera.alejandro.librario.explore.domain.repository.ExploreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExploreDataModule {
    @Provides
    @Singleton
    fun provideGoogleBooksApi(): GoogleBooksApi {
        return Retrofit.Builder()
            .baseUrl(GoogleBooksApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GoogleBooksApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExploreRepository(api: GoogleBooksApi): ExploreRepository {
        return ExploreRepositoryImpl(api = api)
    }
}

