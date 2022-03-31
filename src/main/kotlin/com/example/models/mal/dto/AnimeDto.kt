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

    @SerialName("images")
    val images: ImagesDto,

    @SerialName("synopsis")
    val synopsis: String? = null,

    @SerialName("score")
    val score: Double? = null,

    @SerialName("episodes")
    val episodes: Int? = null
)
