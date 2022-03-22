package com.example.domain

import com.example.api.MyAnimeListApi

suspend fun getCharacterById(characterId: String) = MyAnimeListApi.getCharacterById(characterId)