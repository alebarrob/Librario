package barrera.alejandro.librario.books.data.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import barrera.alejandro.librario.books.data.character.entity.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters WHERE bookId = :bookId")
    fun getBookCharacters(bookId: Int): Flow<List<Character>>

    @Query("SELECT color FROM characters WHERE id = :characterId")
    fun getCharacterColor(characterId: Int): Flow<String>

    @Query("SELECT portrait FROM characters WHERE id = :characterId")
    fun getCharacterPortrait(characterId: Int): Flow<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: Character)

    @Query("UPDATE characters SET name = :characterName, description = :characterDescription, portrait = :characterPortrait WHERE id = :characterId")
    suspend fun updateCharacter(characterName: String, characterDescription: String, characterPortrait: String, characterId: Int)

    @Query("UPDATE characters SET color = :characterColor WHERE id = :characterId")
    suspend fun changeColor(characterColor: String, characterId: Int)

    @Query("DELETE FROM characters WHERE id = :characterId")
    suspend fun deleteCharacter(characterId: Int)
}