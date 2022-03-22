package com.example.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.callbackQuery

fun Bot.Builder.configureDispatch() {
    dispatch {
        topAnimeCommand()
        findCharacterCommand()

        findCharacterInlineQuery()
        inlineMessageCallbackQuery()
    }
}