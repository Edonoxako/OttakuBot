package com.example.domain

import com.example.api.MyAnimeListApi
import com.example.models.mal.domain.InlineArticleSuggestItem
import com.example.models.mal.domain.ResultMessage
import com.example.models.mal.domain.SearchResultEntry
import com.example.models.mal.dto.AnimeDto
import com.example.util.url

suspend fun searchAnimeByTitle(title: String): List<SearchResultEntry> {
    return MyAnimeListApi.searchAnimeByName(title)
        .map(::createSearchResultEntry)
}

private fun createSearchResultEntry(animeDto: AnimeDto): SearchResultEntry {
    return SearchResultEntry(
        suggestItem = createSuggestItem(animeDto),
        resultMessage = ResultMessage(
            resultText = createResultText(animeDto)
        )
    )
}

private fun createSuggestItem(animeDto: AnimeDto) = InlineArticleSuggestItem(
    id = animeDto.malId.toString(),
    title = animeDto.title,
    thumbUrl = animeDto.images.url,
    description = "Anime\n★ ${animeDto.score ?: ""}"
)

private fun createResultText(animeDto: AnimeDto) = buildString {
    appendLine("<b>Anime: </b>${animeDto.title}")
    if (animeDto.score != null) appendLine("★ ${animeDto.score}")
    appendLine()
    if (animeDto.synopsis != null) {
        appendLine(formatSynopsisText(animeDto.synopsis))
        appendLine()
    }
    appendLine(animeDto.malUrl)

}

private fun formatSynopsisText(synopsis: String): String {
    return if (synopsis.length > 500) {
        synopsis.split("\n\n").first()
    } else {
        synopsis
    }
}