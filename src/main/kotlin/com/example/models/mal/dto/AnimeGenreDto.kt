package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenreDto(

    @SerialName("mal_id")
    val malId: Long,

    @SerialName("name")
    val name: String,

    @SerialName("url")
    val malUrl: String,
)