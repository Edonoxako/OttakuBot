package com.example.api.dsl

import io.ktor.client.*

class TopApiBuilder(client: HttpClient, previousApiNode: JikanApiBuilder) : JikanApiBuilder(client, previousApiNode) {

    override val pathSegment: String = "top"

    fun anime() = LeafEndpointApiBuilder(
        client = client,
        previousApiNode = this,
        pathSegment = "anime"
    )
}