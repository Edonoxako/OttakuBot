package com.example.formatter

import com.example.models.mal.domain.OtherVoceActorCharacters
import com.example.models.mal.dto.AnimeDto

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
