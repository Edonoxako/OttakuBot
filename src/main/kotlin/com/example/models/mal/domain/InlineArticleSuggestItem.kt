package com.example.models.mal.domain

data class InlineArticleSuggestItem(
    val id: String,
    val title: String,
    val thumbUrl: String? = null,
    val description: String? = null
)
