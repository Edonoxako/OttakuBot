package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeStudioDto(

    @SerialName("mal_id")
    val malId: Long,

    @SerialName("name")
    val name: String,

    @SerialName("url")
    val malUrl: String,
)
