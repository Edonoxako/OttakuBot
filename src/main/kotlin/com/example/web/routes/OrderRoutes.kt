package com.example.web.routes

import com.example.api.MyAnimeListApi
import com.example.models.orderStorage
import com.example.util.findByIdAndRespond
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrderRoute() {
    get("/order/{id}") {
        call.findByIdAndRespond { id ->
            orderStorage.find { it.id == id }
        }
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id}/total") {
        call.findByIdAndRespond { id ->
            orderStorage.find { it.id == id }
                ?.contents
                ?.sumOf { it.price * it.amount }
        }
    }
}

fun Route.topAnimeListRoute() {
    get("/anime/top") {
        val topAnimeList = MyAnimeListApi.getAnimeTopList()
        call.respond(topAnimeList)
    }
}