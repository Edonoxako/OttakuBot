package com.example.domain

import com.example.api.MyAnimeListApi

suspend fun searchCharacterByName(name: String) = MyAnimeListApi.searchCharacterByName(name)