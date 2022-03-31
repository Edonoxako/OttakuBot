package com.example.domain

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

suspend fun searchEverywhere(searchQuery: String) = coroutineScope {
    val charactersSearchDeferred = async { searchCharacterByName(searchQuery) }
    val animeSearchDeferred = async { searchAnimeByTitle(searchQuery) }

    val characters = charactersSearchDeferred.await()
    val animes = animeSearchDeferred.await()

    if (characters.size > animes.size) {
        characters + animes
    } else {
        animes + characters
    }
}