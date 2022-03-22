package com.example.web.plugins

import com.example.models.Customer
import com.example.models.customerStorage
import com.example.web.routes.getOrderRoute
import com.example.web.routes.listOrdersRoute
import com.example.web.routes.topAnimeListRoute
import com.example.web.routes.totalizeOrderRoute
import com.example.util.findByIdAndRespond
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.util.reflect.*

fun Application.configureRouting() {

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
    routing {
        customerRouting()
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
    routing {
        topAnimeListRoute()
    }
}

private fun Routing.customerRouting() {
    route("/customer") {
        get { call.getCustomers() }
        get("{id}") {
            call.findByIdAndRespond { id -> customerStorage.find { it.id == id } }
        }
        post {
            val newCustomer = call.receive<Customer>()
            customerStorage.add(newCustomer)
            call.respondText("Customer successfully created", status = HttpStatusCode.Created)
        }
        delete("{id}") {

            val id = call.parameters["id"] ?: return@delete call.respondText(
                text = "id parameter is incorrect",
                status = HttpStatusCode.BadRequest
            )

            val removed = customerStorage.removeIf { it.id == id }

            val (resultText, status) = if (removed) {
                "Customer with id == $id removed successfully" to HttpStatusCode.Accepted
            } else {
                "Customer with id == $id cannot be found. Perhaps it has been already removed" to HttpStatusCode.NotFound
            }

            call.respondText(resultText, status = status)
        }
    }
}

private suspend fun ApplicationCall.getCustomers() {
    if (customerStorage.isNotEmpty()) {
        respond(customerStorage)
    } else {
        respondText(text = "No customers found", status = HttpStatusCode.NotFound)
    }
}
