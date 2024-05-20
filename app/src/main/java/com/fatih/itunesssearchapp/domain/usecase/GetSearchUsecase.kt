package com.fatih.itunesssearchapp.domain.usecase

import com.fatih.itunesssearchapp.data.dto.SearchDto
import com.fatih.itunesssearchapp.data.dto.toSearchModel
import com.fatih.itunesssearchapp.data.repository.SearchRepostoryImpl
import com.fatih.itunesssearchapp.presentation.viewmodel.SearchState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GetSearchUsecase @Inject constructor(private val repository: SearchRepostoryImpl) {
    fun executeloadSearch(term : String, entity : String, callback: (SearchState) -> Unit) {
        repository.getSearch(term, entity).enqueue(object : Callback<SearchDto> {
            override fun onResponse(call: Call<SearchDto>, response: Response<SearchDto>) {
                if (response.isSuccessful) {
                    val newsModel = response.body()?.toSearchModel()
                    if (newsModel != null){
                        callback(SearchState.Success(newsModel))
                    } else {
                        callback(SearchState.Error("News data is null"))
                    }
                } else {
                    callback(SearchState.Error("Failed to fetch news data"))
                }
            }
            override fun onFailure(call: Call<SearchDto>, t: Throwable) {
                callback(SearchState.Error("Network request failed"))
            }
        })
    }
}