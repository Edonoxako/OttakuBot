package com.example.api.dsl

import io.ktor.client.*

class RootApiBuilder(client: HttpClient) : JikanApiBuilder(client) {

    override val pathSegment: String = "v4"

    fun top() = TopApiBuilder(
        client = client,
        previousApiNode = this
    )

    fun searchCharacter(
        limit: Int = 10,
        orderBy: CharactersOrderBy = CharactersOrderBy.FAVORITES,
        sort: Sort = Sort.ASC,
        searchQuery: String? = null
    ) = CharacterApiBuilder(
        client = client,
        previousApiNode = this,
        limit = limit,
        orderBy = orderBy,
        sort = sort,
        searchQuery = searchQuery
    )

    fun searchAnime(
        limit: Int = 10,
        orderBy: AnimeOrderBy = AnimeOrderBy.SCORE,
        sort: Sort = Sort.ASC,
        searchQuery: String? = null
    ) = AnimeApiBuilder(
        client = client,
        previousApiNode = this,
        limit = limit,
        orderBy = orderBy,
        sort = sort,
        searchQuery = searchQuery
    )

    fun character(id: String) = SpecificCharacterApiBuilder(
        client = client,
        previousApiNode = this,
        characterId = id
    )

    fun person(id: String) = SpecificPersonApiBuilder(
        client = client,
        previousApiNode = this,
        personId = id
    )
}