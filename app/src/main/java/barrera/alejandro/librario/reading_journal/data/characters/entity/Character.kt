package barrera.alejandro.librario.reading_journal.data.characters.entity

import androidx.room.*
import barrera.alejandro.librario.reading_journal.data.books.entity.Book

@Entity(
    tableName = "characters",
    indices = [Index(value = ["bookId"], name = "book_id_index")],
    foreignKeys = [
        ForeignKey(
            entity = Book::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("bookId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "bookId")
    val bookId: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "portrait")
    val portrait: String,
)
