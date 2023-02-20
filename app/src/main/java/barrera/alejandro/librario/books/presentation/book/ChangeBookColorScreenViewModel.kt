package barrera.alejandro.librario.books.presentation.book

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.librario.books.data.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeBookColorScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val bookRepositoryImpl: BookRepository
) : ViewModel() {
    private val _bookId = MutableStateFlow(savedStateHandle.get<Int>("bookId")!!)
    val bookId: Flow<Int> get() = _bookId

    fun changeColor(bookColor: String, bookId: Int) {
        viewModelScope.launch {
            bookRepositoryImpl.changeColor(bookColor, bookId)
        }
    }
}