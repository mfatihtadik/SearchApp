package com.fatih.itunesssearchapp.domain.repository

import com.fatih.itunesssearchapp.data.dto.SearchDto
import retrofit2.Call

interface SeachRepository {
    fun getSearch(term : String, entity : String): Call<SearchDto>
}