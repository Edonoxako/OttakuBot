package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceRoleDto(

    @SerialName("role")
    val role: String,

    @SerialName("anime")
    val anime: AnimeDto,

    @SerialName("character")
    val character: CharacterDto
)
