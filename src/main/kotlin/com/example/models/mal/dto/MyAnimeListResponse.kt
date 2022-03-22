package com.example.models.mal.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAnimeListResponseCollection<T>(

    @SerialName("pagination")
    val pagination: Pagination? = null,

    @SerialName("data")
    val data: List<T>
)

@Serializable
data class MyAnimeListResponseSingle<T>(

    @SerialName("data")
    val data: T
)

@Serializable
data class Pagination(

    @SerialName("last_visible_page")
    val lastVisiblePage: Int,

    @SerialName("has_next_page")
    val hasNextPage: Boolean
)

@Serializable
data class ImagesDto(

    @SerialName("jpg")
    val jpg: ImagesUrlsDto? = null,

    @SerialName("webp")
    val webp: ImagesUrlsDto? = null
)

@Serializable
data class ImagesUrlsDto(

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("small_image_url")
    val smallImageUrl: String? = null
)