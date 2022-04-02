package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeAiringDatesDto(

    @SerialName("from")
    val from: String? = null,

    @SerialName("to")
    val to: String? = null,

    @SerialName("string")
    val formattedByServer: String? = null
)
