package com.fatih.itunesssearchapp.data.repository

import com.fatih.itunesssearchapp.data.dto.SearchDto
import com.fatih.itunesssearchapp.data.service.ApiServices
import com.fatih.itunesssearchapp.domain.repository.SeachRepository
import retrofit2.Call
import javax.inject.Inject

class SearchRepostoryImpl @Inject constructor(private val api : ApiServices) : SeachRepository {
    override fun getSearch(term: String, entity: String): Call<SearchDto> {
        return api.getViews(term = term,entity=entity)
    }
}

