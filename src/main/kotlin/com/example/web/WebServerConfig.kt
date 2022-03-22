package com.example.web

import com.example.web.plugins.configureRouting
import com.example.web.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun setUpWebServer() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}