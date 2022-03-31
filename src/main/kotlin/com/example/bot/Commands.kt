package com.example.bot

import com.example.domain.*
import com.example.formatter.formatTopAnimeList
import com.example.formatter.formatVoiceActorCharactersList
import com.example.util.createArticleInlineResult
import com.example.util.respond
import com.github.kotlintelegrambot.dispatcher.Dispatcher
import com.github.kotlintelegrambot.dispatcher.callbackQuery
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.inlineQuery
import kotlinx.coroutines.runBlocking

fun Dispatcher.helloCommand() = command("hello") {
    respond("Охаё!")
}

fun Dispatcher.byeCommand() = command("bye") {
    respond("Сайонара!")
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

        val characters = runBlocking { searchEverywhere(query) }
            .map { searchResultEntry ->
                with(searchResultEntry) {
                    createArticleInlineResult(
                        querySuggestItem = suggestItem,
                        queryResultText = resultMessage.resultText,
                        replyMarkup = resultMessage.keyboardMarkup
                    )
                }
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