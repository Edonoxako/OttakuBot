package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

val customerStorage = buildList {

    add(
        Customer(
            id = "1",
            firstName = "Ram",
            lastName = "TheBest",
            email = "barusu@zero.re"
        )
    )

    add(
        Customer(
            id = "2",
            firstName = "Rem",
            lastName = "NotTheBestButAlsoGood",
            email = "start.from.beginning@zero.re"
        )
    )
}.toMutableList()
