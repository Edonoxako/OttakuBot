package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(

    @SerialName("mal_id")
    val malId: Long,

    @SerialName("url")
    val malUrl: String,

    @SerialName("title")
    val title: String,

    @SerialName("title_english")
    val englishTitle: String? = null,

    @SerialName("images")
    val images: ImagesDto,

    @SerialName("synopsis")
    val synopsis: String? = null,

    @SerialName("score")
    val score: Double? = null,

    @SerialName("scored_by")
    val scoredBy: Int? = null,

    @SerialName("episodes")
    val episodes: Int? = null,

    @SerialName("year")
    val year: Int? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("studios")
    val studios: List<AnimeStudioDto>? = null,

    @SerialName("genres")
    val genres: List<AnimeGenreDto>? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("trailer")
    val trailer: AnimeTrailerDto? = null,

    @SerialName("aired")
    val aired: AnimeAiringDatesDto? = null,

    @SerialName("airing")
    val airing: Boolean? = null,

    @SerialName("rating")
    val rating: String? = null
)
