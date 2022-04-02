package com.example.domain

import com.example.api.MyAnimeListApi
import com.example.core.template.*
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
            resultText = createAnimeArticle(animeDto)
        )
    )
}

private fun createSuggestItem(animeDto: AnimeDto) = InlineArticleSuggestItem(
    id = animeDto.malId.toString(),
    title = animeDto.title,
    thumbUrl = animeDto.images.url,
    description = article {
        line { text("Anime") }
        animeSubtitle(animeDto)
    }
)

fun createAnimeArticle(animeDto: AnimeDto) = article {
    paragraph {
        line {
            bold {
                if (animeDto.englishTitle != null) {
                    text(animeDto.englishTitle)
                    text(" / ")
                }
                text(animeDto.title)
            }
        }
    }

    paragraph {
        animeSubtitle(animeDto)
    }

    paragraph {
        if (animeDto.status != STATUS_FINISHED_AIRING) {
            listItem(animeDto.status?.let { "Status: $it" })
        }
        listItem(animeDto.aired?.formattedByServer?.let { "Airing dates: $it" })
        listItem(animeDto.genres?.joinToString(prefix = "Genres: ") { it.name })
        listItem(animeDto.studios?.joinToString(prefix = "Studios: ") { it.name })
    }


    bold {
        link(animeDto.malUrl, "Read on My Anime List")
        if (animeDto.trailer?.url != null) {
            text(" • ")
            link(animeDto.trailer.url, "Watch trailer")
        }
    }
}

private fun ArticleScaffold.animeSubtitle(animeDto: AnimeDto) {
    line {
        horizontalList(
            delimiter = " • ",
            ratingString(animeDto.score, animeDto.scoredBy),
            animeDto.type,
            animeDto.year?.toString(),
            animeDto.episodes?.let { "$it episodes" },
            animeDto.rating
        )
    }
}

private const val STATUS_FINISHED_AIRING = "Finished Airing"