package barrera.alejandro.librario.books.data.character.entity

import androidx.room.*
import barrera.alejandro.librario.books.data.book.entity.Book

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

    @ColumnInfo(name = "color")
    val color: String,

    @ColumnInfo(name = "portrait")
    val portrait: String,
)
