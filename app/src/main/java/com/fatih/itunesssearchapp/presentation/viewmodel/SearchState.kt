package com.fatih.itunesssearchapp.presentation.viewmodel

import com.fatih.itunesssearchapp.domain.model.SearchModel

sealed class SearchState {
    data class Success(val searchModel: SearchModel ) : SearchState()
    data class Error(val errorMessage : String) : SearchState()
}