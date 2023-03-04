package barrera.alejandro.librario.core.domain.di

import barrera.alejandro.librario.core.domain.use_case.CoreUseCases
import barrera.alejandro.librario.core.domain.use_case.SlashToDashConverter
import barrera.alejandro.librario.core.domain.use_case.ValidateInfoNotEmpty
import barrera.alejandro.librario.reading_journal.domain.books.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CoreDomainModule {
    @ViewModelScoped
    @Provides
    fun provideCoreUseCases(): CoreUseCases {
        return CoreUseCases(
            validateInfoNotEmpty = ValidateInfoNotEmpty(),
            slashToDashConverter = SlashToDashConverter()
        )
    }
}