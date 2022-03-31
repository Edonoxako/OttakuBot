package com.example.models.mal.domain

import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup

data class SearchResultEntry(
    val suggestItem: InlineArticleSuggestItem,
    val resultMessage: ResultMessage
)

data class ResultMessage(
    val resultText: String,
    val keyboardMarkup: InlineKeyboardMarkup? = null
)