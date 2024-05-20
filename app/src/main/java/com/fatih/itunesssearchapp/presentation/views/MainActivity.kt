package com.fatih.itunesssearchapp.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatih.itunesssearchapp.R
import com.fatih.itunesssearchapp.presentation.adapter.SearchAdapter
import com.fatih.itunesssearchapp.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}