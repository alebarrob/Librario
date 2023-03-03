package barrera.alejandro.librario.explore.domain.di

import barrera.alejandro.librario.explore.domain.repository.ExploreRepository
import barrera.alejandro.librario.explore.domain.use_case.ExploreUseCases
import barrera.alejandro.librario.explore.domain.use_case.SearchGoogleBook
import barrera.alejandro.librario.reading_journal.domain.books.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ExploreDomainModule {
    @ViewModelScoped
    @Provides
    fun provideExploreUseCases(
        exploreRepository: ExploreRepository
    ): ExploreUseCases {
        return ExploreUseCases(
            searchGoogleBook = SearchGoogleBook(exploreRepository)
        )
    }
}