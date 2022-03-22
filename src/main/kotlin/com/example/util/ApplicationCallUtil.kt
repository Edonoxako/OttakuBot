package com.example.util

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend inline fun <reified T : Any> ApplicationCall.findByIdAndRespond(idKey: String = "id", finder: (String) -> T?) {

    val id = parameters[idKey] ?: return respondText(
        text = "$idKey parameter is incorrect",
        status = HttpStatusCode.BadRequest
    )

    val foundEntity = finder.invoke(id) ?: return respondText(
        text = "Entity with $idKey == $id is not found",
        status = HttpStatusCode.NotFound
    )

    respond(foundEntity)
}