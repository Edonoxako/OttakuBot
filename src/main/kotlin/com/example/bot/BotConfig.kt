package com.example.bot

import com.example.ApiKeys
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.logging.LogLevel

fun setUpBot() {
    bot {
        token = ApiKeys.TELEGRAM_BOT_API_KEY
        logLevel = LogLevel.Error
        configureDispatch()
    }.startPolling()
}