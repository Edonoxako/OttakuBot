package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeRoleDto(

    @SerialName("role")
    val role: String,

    @SerialName("anime")
    val animeDto: AnimeDto
)