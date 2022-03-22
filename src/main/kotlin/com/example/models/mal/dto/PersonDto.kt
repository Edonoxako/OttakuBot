package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(

    @SerialName("language")
    val language: String,

    @SerialName("person")
    val details: PersonDetailsDto
)

@Serializable
data class PersonDetailsDto(

    @SerialName("mal_id")
    val malId: Long,

    @SerialName("url")
    val malUrl: String,

    @SerialName("name")
    val name: String
)
