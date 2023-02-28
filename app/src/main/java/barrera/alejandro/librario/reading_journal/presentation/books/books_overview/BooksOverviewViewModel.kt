package barrera.alejandro.librario.reading_journal.presentation.books.books_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.reading_journal.domain.books.use_case.BooksUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksOverviewViewModel @Inject constructor(
    private val booksUseCases: BooksUseCases
) : ViewModel() {
    var state by mutableStateOf(BooksOverviewState())
        private set

    fun onEvent(event: BooksOverviewEvent) {
        when (event) {
            is BooksOverviewEvent.LoadBooks -> {
                loadBooks()
            }
            is BooksOverviewEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is BooksOverviewEvent.OnSearch -> {
                loadBooks()
            }
            is BooksOverviewEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }

    private fun loadBooks() {
        viewModelScope.launch {
            booksUseCases.getAllBooks(state.query).collect { loadedBooks ->
                state = state.copy(books = loadedBooks)
            }
        }
    }
}