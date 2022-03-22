package com.example.domain

import com.example.api.MyAnimeListApi

suspend fun getTopAnimeList() = MyAnimeListApi.getAnimeTopList()