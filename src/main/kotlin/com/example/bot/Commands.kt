package com.example.bot

import com.example.domain.getCharacterById
import com.example.domain.getTopAnimeList
import com.example.domain.searchCharacterByName
import com.example.domain.searchCharacterVoicesByName
import com.example.formatter.formatCharacterInlineResult
import com.example.formatter.formatCharacterInlineSuggest
import com.example.formatter.formatVoiceActorCharactersList
import com.example.formatter.formatTopAnimeList
import com.example.util.createArticleInlineResult
import com.example.util.respond
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.inlineQuery
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.keyboard.InlineKeyboardButton
import kotlinx.coroutines.runBlocking

fun Dispatcher.helloCommand() = command("hello") {
    respond("Охаё!")
}

fun Dispatcher.topAnimeCommand() = command("top") {
    val topAnimeList = runBlocking { getTopAnimeList() }
    respond(formatTopAnimeList(topAnimeList))
}

fun Dispatcher.findCharacterCommand() = command("character") {

    val respondMessage = if (args.isNotEmpty()) {
        args.joinToString()
            .let { runBlocking { searchCharacterVoicesByName(it) } }
            .let(::formatVoiceActorCharactersList)
    } else {
        "Чтобы найти персонажа, мне нужно знать его имя, блин."
    }

    respond(respondMessage)
}

fun Dispatcher.findCharacterInlineQuery() = inlineQuery {

    val query = inlineQuery.query

    if (query.isNotBlank()) {

        val characters = runBlocking { searchCharacterByName(query) }
            .map { character ->
                val suggestItem = formatCharacterInlineSuggest(character)
                val resultTextShort = formatCharacterInlineResult(character, fullAboutText = false)
                val addKeyboardMarkup = character.about != null && character.about.length > 500
                createArticleInlineResult(
                    querySuggestItem = suggestItem,
                    queryResultText = resultTextShort,
                    replyMarkup = if (addKeyboardMarkup) {
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
                )
            }

        respond(characters)
    }
}

fun Dispatcher.inlineMessageCallbackQuery() = callbackQuery {
    val inlineMessageId = callbackQuery.inlineMessageId ?: return@callbackQuery
    val characterId = callbackQuery.data

    val characterMessage = formatCharacterInlineResult(
        characterDto = runBlocking { getCharacterById(characterId) },
        fullAboutText = true
    )

    bot.editMessageText(
        inlineMessageId = inlineMessageId,
        text = characterMessage
    )
}