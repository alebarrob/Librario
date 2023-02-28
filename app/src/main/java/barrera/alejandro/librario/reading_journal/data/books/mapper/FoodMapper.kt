package barrera.alejandro.librario.reading_journal.data.books.mapper

import barrera.alejandro.librario.reading_journal.data.books.entity.BookEntity
import barrera.alejandro.librario.reading_journal.domain.books.model.Book

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        author = author,
        description = description,
        notes = notes
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        author = author,
        description = description,
        notes = notes
    )
}