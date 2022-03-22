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

    @SerialName("episodes")
    val episodes: Int? = null
)
