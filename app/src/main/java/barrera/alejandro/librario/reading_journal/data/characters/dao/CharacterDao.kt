package barrera.alejandro.librario.reading_journal.data.characters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.librario.reading_journal.data.characters.entity.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters WHERE bookId = :bookId")
    fun getBookCharacters(bookId: Int): Flow<List<Character>>

    @Query("SELECT portrait FROM characters WHERE id = :characterId")
    fun getCharacterPortrait(characterId: Int): Flow<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("UPDATE characters SET name = :characterName, description = :characterDescription, portrait = :characterPortrait WHERE id = :characterId")
    suspend fun updateCharacter(characterName: String, characterDescription: String, characterPortrait: String, characterId: Int)

    @Query("DELETE FROM characters WHERE id = :characterId")
    suspend fun deleteCharacter(characterId: Int)
}