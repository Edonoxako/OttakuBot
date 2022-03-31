package com.example.api.dsl

import io.ktor.client.*

class AnimeApiBuilder(
    client: HttpClient,
    previousApiNode: JikanApiBuilder,
    limit: Int,
    orderBy: AnimeOrderBy,
    sort: Sort,
    private val searchQuery: String?
) : JikanApiBuilder(client, previousApiNode) {

    override val pathSegment = "anime"

    override val queryParams = mutableMapOf(
        "limit" to limit.toString(),
        "order_by" to orderBy.rawValue,
        "sort" to sort.rawValue,
    ).apply {
        if (searchQuery != null) put("q", searchQuery)
    }
}

enum class AnimeOrderBy(val rawValue: String) {
    MAL_ID("mal_id"),
    TITLE("title"),
    TYPE("type"),
    RATING("rating"),
    START_DATE("start_date"),
    END_DATE("end_date"),
    EPISODES("episodes"),
    SCORE("score"),
    SCORED_BY("scored_by"),
    RANK("rank"),
    POPULARITY("popularity"),
    MEMBERS("members"),
    FAVORITES("favorites"),
}