package com.example

object ApiKeys {

    val TELEGRAM_BOT_API_KEY = System.getenv("OTTAKU_BOT_API_KEY") ?: error(
        "To run bot you need to set up OTTAKU_BOT_API_KEY environment variable"
    )

}