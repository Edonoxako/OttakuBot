package com.example.bot

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.dispatch

fun Bot.Builder.configureDispatch() {
    dispatch {
        helloCommand()
        byeCommand()
        topAnimeCommand()
        findCharacterCommand()

        findCharacterInlineQuery()
        inlineMessageCallbackQuery()
    }
}