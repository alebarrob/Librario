package barrera.alejandro.librario.reading_journal.data.characters.mapper

import barrera.alejandro.librario.reading_journal.data.characters.entity.CharacterEntity
import barrera.alejandro.librario.reading_journal.domain.characters.model.Character

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        bookId = bookId,
        name = name,
        description = description,
        portraitTag = portraitTag
    )
}

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        bookId = bookId,
        name = name,
        description = description,
        portraitTag = portraitTag
    )
}