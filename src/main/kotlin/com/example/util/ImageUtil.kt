package com.example.util

import com.example.models.mal.dto.ImagesDto

val ImagesDto.url: String?
    get() = webp?.imageUrl ?: jpg?.imageUrl

val ImagesDto.smallUrl: String?
    get() = webp?.smallImageUrl ?: jpg?.smallImageUrl