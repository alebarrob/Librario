package barrera.alejandro.librario.reading_journal.data.characters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("SELECT * FROM characters WHERE bookId = :bookId")
    fun getBookCharacters(bookId: Int): Flow<List<Character>>

    @Query("""
        UPDATE characters
        SET name = :name, description = :description, portraitTag = :portraitTag
        WHERE id = :characterId
    """)
    suspend fun updateCharacter(
        name: String,
        description: String,
        portraitTag: String,
        characterId: Int
    )

    @Query("DELETE FROM characters WHERE id = :characterId")
    suspend fun deleteCharacter(characterId: Int)
}