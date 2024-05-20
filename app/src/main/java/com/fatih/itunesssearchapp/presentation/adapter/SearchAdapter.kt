package com.fatih.itunesssearchapp.presentation.adapter

import android.content.ClipData.Item
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.fatih.itunesssearchapp.R
import com.fatih.itunesssearchapp.data.dto.SearchDto
import com.fatih.itunesssearchapp.data.local.FavDao
import com.fatih.itunesssearchapp.data.local.FavFilmsDatabase
import com.fatih.itunesssearchapp.databinding.ItemSearchBinding
import com.fatih.itunesssearchapp.presentation.util.downloadFromUrl
import com.fatih.itunesssearchapp.presentation.views.MainFragmentDirections
import com.google.android.material.imageview.ShapeableImageView

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val txtFilmAdi : TextView = itemView.findViewById(R.id.filmName)
        val imgGet : ImageView = itemView.findViewById(R.id.imageView)
    }
    private lateinit var binding: ItemSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //binding = ItemSearchBinding.inflate(inflater,parent,false)

        //val view = DataBindingUtil.inflate<ItemSearchBinding>(inflater, R.layout.item_search,parent,false)
        val view = inflater.inflate(R.layout.item_search,parent,false)

        return SearchViewHolder(view)


    }
    //inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {


            holder.txtFilmAdi.text = differ.currentList[position].collectionName
            holder.imgGet.downloadFromUrl(differ.currentList[position].artworkUrl100.toString())
            holder.itemView.setOnClickListener {
                val imgUrl = differ.currentList[position].artworkUrl100.toString()
                val filmAdi = differ.currentList[position].collectionName.toString()
                val filmDiscrp = differ.currentList[position].longDescription.toString()
                val filmLink = differ.currentList[position].previewUrl.toString()
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(imgUrl =imgUrl,filmAdi=filmAdi, filmDescription = filmDiscrp,filmLink)
                Navigation.findNavController(it).navigate(action)
            }


    }

    private val differCallback = object : DiffUtil.ItemCallback<SearchDto.Result>(){
        override fun areItemsTheSame(oldItem: SearchDto.Result, newItem: SearchDto.Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchDto.Result,
            newItem: SearchDto.Result
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)



    //Extensions
    /*
    fun ImageView.downloadFromUrl(url: String?) {
        val circularProgressDrawable = CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            setColorSchemeColors(Color.RED, Color.BLUE,Color.GREEN)
            start()
        }

        Glide.with(context)
            .load(url)
            .placeholder(circularProgressDrawable)
            .into(this)
    }

     */





}