package com.fatih.itunesssearchapp.data.service

import com.fatih.itunesssearchapp.data.dto.SearchDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/search")
    fun getViews(
        @Query("term") term : String,
        @Query("entity") entity: String
    ) : Call<SearchDto>
}