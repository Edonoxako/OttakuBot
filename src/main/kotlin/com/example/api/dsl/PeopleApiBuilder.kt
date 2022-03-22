package com.example.api.dsl

import io.ktor.client.*

class SpecificPersonApiBuilder(
    client: HttpClient,
    previousApiNode: JikanApiBuilder,
    personId: String
) : JikanApiBuilder(client, previousApiNode) {

    override val pathSegment: String = "people/$personId"

    fun voices() = LeafEndpointApiBuilder(
        client = client,
        previousApiNode = this,
        pathSegment = "voices"
    )
}