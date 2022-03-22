package com.example.api

import com.example.api.dsl.Sort
import com.example.api.dsl.jikanApiRequest
import com.example.models.mal.dto.*
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.json.Json

object MyAnimeListApi {

    private val client = HttpClient(CIO) {
        install(Logging)
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            url("https://api.jikan.moe/")
        }
        install(HttpRedirect) {
            allowHttpsDowngrade = true
        }
        install(HttpCache)
    }

    private val api = jikanApiRequest(client)

    suspend fun getAnimeTopList() = api
        .top()
        .anime()
        .getCollection<AnimeDto>()

    suspend fun searchCharacterByName(characterName: String) = withRetryCollection {
        api.searchCharacter(
            searchQuery = characterName,
            sort = Sort.DESC
        ).getCollection<CharacterDto>()
    }

    suspend fun getCharacterById(characterId: String) = withRetrySingle {
        api.character(characterId).getSingle<CharacterDto>()
    }

    suspend fun getCharactersVoiceActors(characterId: String) = withRetryCollection {
        api.character(characterId)
            .voices()
            .getCollection<PersonDto>()
    }


    suspend fun getPersonVoices(personId: String) = withRetryCollection {
        api.person(personId)
            .voices()
            .getCollection<VoiceRoleDto>()
    }

    suspend fun getCharacterAnime(characterId: String) = withRetryCollection {
        api.character(characterId)
            .anime()
            .getCollection<AnimeRoleDto>()
    }

    private suspend fun <T> withRetryCollection(apiCall: suspend () -> List<T>) = flow {
        emit(apiCall.invoke())
    }.retry(10) {
        delay(1000)
        true
    }.toList().flatten()

    private suspend fun <T> withRetrySingle(apiCall: suspend () -> T) = flow {
        emit(apiCall.invoke())
    }.retry(10) {
        delay(1000)
        true
    }.first()
}