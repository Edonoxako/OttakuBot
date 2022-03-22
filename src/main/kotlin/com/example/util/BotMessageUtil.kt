package com.example.util

import com.example.models.mal.domain.InlineArticleSuggestItem
import com.github.kotlintelegrambot.dispatcher.handlers.CommandHandlerEnvironment
import com.github.kotlintelegrambot.dispatcher.handlers.InlineQueryHandlerEnvironment
import com.github.kotlintelegrambot.entities.ChatId
import com.github.kotlintelegrambot.entities.InlineKeyboardMarkup
import com.github.kotlintelegrambot.entities.ParseMode
import com.github.kotlintelegrambot.entities.inlinequeryresults.InlineQueryResult
import com.github.kotlintelegrambot.entities.inlinequeryresults.InputMessageContent

fun CommandHandlerEnvironment.respond(text: String) {
    bot.sendMessage(
        ChatId.fromId(message.chat.id),
        text = text,
        parseMode = ParseMode.HTML
    )
}

fun InlineQueryHandlerEnvironment.respond(inlineQueryResults: List<InlineQueryResult>) {
    bot.answerInlineQuery(
        inlineQueryId = inlineQuery.id,
        inlineQueryResults = inlineQueryResults
    )
}

fun createTextMessageContent(text: String): InputMessageContent.Text {
    return InputMessageContent.Text(
        messageText = text,
        disableWebPagePreview = false
    )
}

fun createArticleInlineResult(
    querySuggestItem: InlineArticleSuggestItem,
    queryResultText: String,
    replyMarkup: InlineKeyboardMarkup? = null
) = with(querySuggestItem) {
    InlineQueryResult.Article(
        id = id,
        title = title,
        thumbUrl = thumbUrl,
        description = description,
        replyMarkup = replyMarkup,
        inputMessageContent = createTextMessageContent(queryResultText)
    )
}