package com.fatih.itunesssearchapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fatih.itunesssearchapp.data.local.FavFilms
import com.fatih.itunesssearchapp.data.local.FavFilmsDatabase
import com.fatih.itunesssearchapp.domain.model.SearchModel
import com.fatih.itunesssearchapp.domain.usecase.GetSearchUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchUsecase: GetSearchUsecase, application: Application) : BaseViewModel(application) {
    private val _favFilms = MutableLiveData<List<FavFilms>>()
    val favFilms: LiveData<List<FavFilms>> = _favFilms

    private val _search = MutableLiveData<SearchModel>()
    val currentSearch: LiveData<SearchModel> = _search

    fun loadNews(term : String, entity : String){
            getSearchUsecase.executeloadSearch(term,entity){ searchState ->
                when(searchState){
                    is SearchState.Success -> {
                        _search.value = searchState.searchModel
                    }
                    is SearchState.Error -> {
                        //Handle Fail
                    }
                }
            }
    }
     suspend fun storeInSql(filmAdi : String, imgUrl : String, desc: String){
        val dao =FavFilmsDatabase(getApplication()).favDao()
        val favFilms =FavFilms(filmAdi,imgUrl,desc)
        dao.insertFilm(favFilms)
    }

    fun fetchAllFavFilms() {
        viewModelScope.launch {
            val films = FavFilmsDatabase(getApplication()).favDao().getAll()
            _favFilms.postValue(films)
        }
    }

    fun deleteFilm(favFilm: FavFilms) {
        viewModelScope.launch {
            FavFilmsDatabase(getApplication()).favDao().deleteFilm(favFilm)
            fetchAllFavFilms()
        }
    }

}