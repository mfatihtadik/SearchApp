package com.fatih.itunesssearchapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.fatih.itunesssearchapp.R
import com.fatih.itunesssearchapp.data.dto.SearchDto
import com.fatih.itunesssearchapp.data.local.FavDao
import com.fatih.itunesssearchapp.data.local.FavFilms
import com.fatih.itunesssearchapp.data.local.FavFilmsDatabase
import com.fatih.itunesssearchapp.presentation.util.downloadFromUrl


class FavFilmAdapter : RecyclerView.Adapter<FavFilmAdapter.FavViewHolder>() {

    class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtFilmAdi : TextView = itemView.findViewById(R.id.filmAdi)
        val imgGet : ImageView = itemView.findViewById(R.id.imageView2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_rroom,parent,false)
        return FavViewHolder(view)
    }
    override fun getItemCount() = differ.currentList.size
    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.txtFilmAdi.text = differ.currentList[position].fName
        holder.imgGet.downloadFromUrl(differ.currentList[position].fImg)
    }
    private val differCallback = object : DiffUtil.ItemCallback<FavFilms>(){
        override fun areItemsTheSame(oldItem: FavFilms, newItem: FavFilms): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(
            oldItem: FavFilms,
            newItem: FavFilms
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
}