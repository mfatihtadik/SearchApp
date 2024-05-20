package com.fatih.itunesssearchapp.presentation.views

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatih.itunesssearchapp.R
import com.fatih.itunesssearchapp.databinding.FragmentMainBinding
import com.fatih.itunesssearchapp.presentation.adapter.SearchAdapter
import com.fatih.itunesssearchapp.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter by lazy { SearchAdapter() }
    var word = "batman"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.loadNews(term = word, entity = "movie")
        searchViewModel.currentSearch.observe(viewLifecycleOwner, Observer{
            it?.let {
                searchAdapter.differ.submitList(it.results)
                binding.recyclerView.layoutManager = GridLayoutManager(context,2)
                binding.recyclerView.adapter = searchAdapter
            }
        })
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.loadNews(term = query!!, entity = "movie")
                searchViewModel.currentSearch.observe(viewLifecycleOwner, Observer{
                    it?.let {
                        searchAdapter.differ.submitList(it.results)
                        binding.recyclerView.layoutManager = GridLayoutManager(context,2)
                        binding.recyclerView.adapter = searchAdapter
                        word = query
                    }
                })
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        binding.gitFav.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToFavoriteFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}