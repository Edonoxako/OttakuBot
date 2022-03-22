package com.example.api.dsl

import io.ktor.client.*

class CharacterApiBuilder(
    client: HttpClient,
    previousApiNode: JikanApiBuilder,
    limit: Int,
    orderBy: CharactersOrderBy,
    sort: Sort,
    private val searchQuery: String?
) : JikanApiBuilder(client, previousApiNode) {

    override val pathSegment: String = "characters"

    override val queryParams = mutableMapOf(
        "limit" to limit.toString(),
        "order_by" to orderBy.rawValue,
        "sort" to sort.rawValue,
    ).apply {
        if (searchQuery != null) put("q", searchQuery)
    }
}

class SpecificCharacterApiBuilder(
    client: HttpClient,
    previousApiNode: JikanApiBuilder,
    characterId: String
) : JikanApiBuilder(client, previousApiNode) {

    override val pathSegment: String = "characters/$characterId"

    fun anime() = LeafEndpointApiBuilder(
        client = client,
        previousApiNode = this,
        pathSegment = "anime"
    )

    fun voices() = LeafEndpointApiBuilder(
        client = client,
        previousApiNode = this,
        pathSegment = "voices"
    )
}

enum class CharactersOrderBy(val rawValue: String) {
    MAL_ID("mal_id"), NAME("name"), FAVORITES("favorites")
}