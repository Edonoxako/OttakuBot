package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(

    @SerialName("mal_id")
    val malId: Long,

    @SerialName("url")
    val malUrl: String,

    @SerialName("name")
    val name: String,

    @SerialName("about")
    val about: String? = null,

    @SerialName("images")
    val images: ImagesDto? = null
)
