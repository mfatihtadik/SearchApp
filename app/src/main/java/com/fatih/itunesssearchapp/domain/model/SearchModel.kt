package com.fatih.itunesssearchapp.domain.model

import com.fatih.itunesssearchapp.data.dto.SearchDto
import com.google.gson.annotations.SerializedName

data class SearchModel(
    val results: List<SearchDto.Result?>?
)
