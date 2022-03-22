package com.example.domain

import com.example.api.MyAnimeListApi
import com.example.models.mal.domain.OtherVoceActorCharacters
import com.example.models.mal.dto.CharacterDto
import com.example.models.mal.dto.PersonDto
import kotlinx.coroutines.delay

suspend fun searchCharacterVoicesByName(name: String): OtherVoceActorCharacters? {

    val character = MyAnimeListApi.searchCharacterByName(name).firstOrNull() ?: return null
    val animeRole = MyAnimeListApi.getCharacterAnime(character.malId.toString()).firstOrNull() ?: return null
    delay(1000)
    val person = getCharacterVoiceActors(character).firstOrNull() ?: return null
    val voiceRoles = MyAnimeListApi.getPersonVoices(person.details.malId.toString()).take(5)

    return OtherVoceActorCharacters(
        character = character,
        anime = animeRole.animeDto,
        person = person,
        voiceRoles = voiceRoles
    )
}

private suspend fun getCharacterVoiceActors(character: CharacterDto): List<PersonDto> {
    return MyAnimeListApi.getCharactersVoiceActors(character.malId.toString())
        .filter { it.language == "Japanese" }
        .take(1)
}