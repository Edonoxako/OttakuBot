package com.example.formatter

import com.example.models.mal.domain.InlineArticleSuggestItem
import com.example.models.mal.domain.OtherVoceActorCharacters
import com.example.models.mal.dto.AnimeDto
import com.example.models.mal.dto.CharacterDto
import com.example.util.url

fun formatTopAnimeList(topAnimeList: List<AnimeDto>) = topAnimeList.joinToString(
    separator = "\n",
    transform = { "<b>${it.title}</b>" },
)

fun formatVoiceActorCharactersList(otherVoceActorCharacters: OtherVoceActorCharacters?): String {
    return if (otherVoceActorCharacters == null) {
        "Не удалось ничего найти"
    } else {
        with(otherVoceActorCharacters) {
            val roles = voiceRoles.joinToString(
                separator = "\n\n",
                transform = { "<b>Персонаж:</b> ${it.character.name}\n<b>Роль:</b> ${it.role}\n<b>Аниме:</b> ${it.anime.title}" }
            )

            buildString {
                appendLine("<b>Сэйю:</b> ${person.details.name}")
                appendLine("<b>Персонаж:</b> ${character.name}")
                appendLine("<b>Аниме:</b> ${anime.title}")
                appendLine()
                appendLine("<b>==Другие роли==</b>")
                appendLine()
                append(roles)
            }
        }
    }
}

fun formatCharacterInlineSuggest(characterDto: CharacterDto): InlineArticleSuggestItem {
    return with(characterDto) {
        InlineArticleSuggestItem(
            id = malId.toString(),
            title = name,
            thumbUrl = images?.url,
            description = about
        )
    }
}

fun formatCharacterInlineResult(characterDto: CharacterDto, fullAboutText: Boolean = false): String {
    return buildString {
        appendLine(characterDto.name)
        appendLine()

        val about = characterDto.about
        if (about != null) {
            val aboutText = if (fullAboutText) {
                about
            } else {
                formatAboutText(about)
            }
            appendLine(aboutText)
            appendLine()
        }

        appendLine(characterDto.malUrl)
    }
}

fun formatAboutText(about: String): String {
    return if (about.length > 500) {
        about.split("\n\n").first()
    } else {
        about
    }
}
