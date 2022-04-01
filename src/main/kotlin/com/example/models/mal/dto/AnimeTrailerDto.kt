package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTrailerDto(

    @SerialName("url")
    val url: String? = null
)