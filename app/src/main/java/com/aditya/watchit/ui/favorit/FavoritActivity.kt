package com.aditya.watchit.ui.favorit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.databinding.ActivityFavoritBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.viewmodel.ViewModelFactory
import com.aditya.watchit.vo.Status

class FavoritActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritBinding
    private lateinit var viewModel: FavoritViewModel
    private val favoritAdapter = FavoritAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pgsBar.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoritViewModel::class.java]

        viewModel.getAllFavorit().observe(this,{
            if (it != null) {
                binding.pgsBar.visibility = View.GONE
                favoritAdapter.submitList(it)
                favoritAdapter.notifyDataSetChanged()
            } else {
                binding.pgsBar.visibility = View.GONE
                binding.tvNoFavorit.visibility = View.VISIBLE
            }
            binding.rvFavorit.apply {
                layoutManager = LinearLayoutManager(this@FavoritActivity)
                setHasFixedSize(true)
                adapter = favoritAdapter
            }
        })
        favoritAdapter.setOnItemClickedCallback(object : FavoritAdapter.OnFavoritClick{
            override fun onItemClicked(favoritEntity: FavoritEntity) {
                val intent = Intent(this@FavoritActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA_FAVORIT, favoritEntity)
                }
                startActivity(intent)
            }

        })
    }
}