package com.example.api.dsl

import com.example.models.mal.dto.MyAnimeListResponseCollection
import com.example.models.mal.dto.MyAnimeListResponseSingle
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*


fun jikanApiRequest(client: HttpClient): RootApiBuilder {
    return RootApiBuilder(client)
}

abstract class JikanApiBuilder(
    val client: HttpClient,
    private val previousApiNode: JikanApiBuilder? = null
) {

    abstract val pathSegment: String

    protected open val queryParams: Map<String, String>? = null

    fun assembleUrl(urlBuilder: URLBuilder) {
        previousApiNode?.assembleUrl(urlBuilder)
        urlBuilder.appendPathSegments(pathSegment)
        queryParams?.forEach { (key, value) ->
            urlBuilder.parameters.append(key, value)
        }
    }

    suspend inline fun <reified T> getCollection(): List<T> = client.get {
        url {
            assembleUrl(this)
        }
    }.body<MyAnimeListResponseCollection<T>>().data

    suspend inline fun <reified T> getSingle(): T = client.get {
        url {
            assembleUrl(this)
        }
    }.body<MyAnimeListResponseSingle<T>>().data
}

class LeafEndpointApiBuilder(
    client: HttpClient,
    previousApiNode: JikanApiBuilder,
    override val pathSegment: String,
) : JikanApiBuilder(client, previousApiNode)

enum class Sort(val rawValue: String) {
    ASC("asc"), DESC("desc")
}