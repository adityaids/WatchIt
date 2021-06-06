package com.aditya.watchit.ui.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.R
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.databinding.ActivityFavoritBinding
import com.aditya.watchit.viewmodel.ViewModelFactory

class FavoritActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritBinding
    private lateinit var viewModel: FavoritViewModel
    private val favoritAdapter = FavoritAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoritViewModel::class.java]

        binding.rvFavorit.apply {
            layoutManager = LinearLayoutManager(this@FavoritActivity)
            setHasFixedSize(true)
            adapter = favoritAdapter
        }

        viewModel.getAllFavorit().observe(this, ::setFavoritList)
    }

    private fun setFavoritList(result: List<FavoritEntity>){
        favoritAdapter.setFavorit(result)
        favoritAdapter.notifyDataSetChanged()
    }
}