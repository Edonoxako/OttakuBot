package com.example.models.mal.domain

import com.example.models.mal.dto.AnimeDto
import com.example.models.mal.dto.CharacterDto
import com.example.models.mal.dto.PersonDto
import com.example.models.mal.dto.VoiceRoleDto

data class OtherVoceActorCharacters(
    val character: CharacterDto,
    val anime: AnimeDto,
    val person: PersonDto,
    val voiceRoles: List<VoiceRoleDto>
)
