package com.example.domain

import com.example.api.MyAnimeListApi
import com.example.models.mal.domain.InlineArticleSuggestItem
import com.example.models.mal.domain.ResultMessage
import com.example.models.mal.domain.SearchResultEntry
import com.example.models.mal.dto.CharacterDto
import com.example.util.url
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton

suspend fun searchCharacterByName(name: String): List<SearchResultEntry> {
    return MyAnimeListApi.searchCharacterByName(name)
        .map(::createSearchResultEntry)
}

private fun createSearchResultEntry(characterDto: CharacterDto): SearchResultEntry {
    return SearchResultEntry(
        suggestItem = formatCharacterInlineSuggest(characterDto),
        resultMessage = ResultMessage(
            resultText = formatCharacterInlineResult(characterDto),
            keyboardMarkup = createKeyboardMarkup(characterDto)
        )
    )
}

fun formatCharacterInlineSuggest(characterDto: CharacterDto): InlineArticleSuggestItem {
    return with(characterDto) {
        InlineArticleSuggestItem(
            id = malId.toString(),
            title = name,
            thumbUrl = images?.url,
            description = "Character\n${characterDto.about ?: ""}"
        )
    }
}

fun formatCharacterInlineResult(characterDto: CharacterDto, fullAboutText: Boolean = false): String {
    return buildString {
        appendLine("<b>Character: </b>${characterDto.name}")
        appendLine()

        val about = characterDto.about
        if (about != null) {
            val aboutText = if (fullAboutText) {
                about
            } else {
                formatAboutText(about)
            }
            appendLine(aboutText)
            appendLine()
        }

        appendLine(characterDto.malUrl)
    }
}

fun formatAboutText(about: String): String {
    return if (about.length > 500) {
        about.split("\n\n").first()
    } else {
        about
    }
}

private fun createKeyboardMarkup(character: CharacterDto): InlineKeyboardMarkup? {
    val addKeyboardMarkup = character.about != null && character.about.length > 500
    return if (addKeyboardMarkup) {
        InlineKeyboardMarkup.create(
            listOf(
                InlineKeyboardButton.CallbackData(
                    text = "Learn more about ${character.name}",
                    callbackData = character.malId.toString()
                )
            ),
        )
    } else {
        null
    }
}