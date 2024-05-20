package com.fatih.itunesssearchapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Query("SELECT * FROM FavFilms")
    suspend fun getAll(): List<FavFilms>
    @Insert
    suspend fun insertFilm(film : FavFilms)
    @Delete
    suspend fun deleteFilm(film: FavFilms)

}