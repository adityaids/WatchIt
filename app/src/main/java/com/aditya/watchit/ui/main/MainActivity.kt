package com.aditya.watchit.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.ActivityMainBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        popularAdapter = PopularAdapter()
        val viewpager = Viewpager(this, supportFragmentManager)
        binding.viewpager.adapter = viewpager
        binding.tabs.setupWithViewPager(binding.viewpager)
        supportActionBar?.elevation = 0f

        val factory = ViewModelFactory.getInstance(this)
        binding.pgsBar.visibility = View.VISIBLE
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularAdapter
        }
        mainViewModel.getPopularList().observe(this, {
            binding.pgsBar.visibility = View.GONE
            popularAdapter.setPopularFilm(it)
            popularAdapter.notifyDataSetChanged()
        })

        popularAdapter.setOnItemClick(object : OnClickedItem{
            override fun onClickedItemCallback(filmModel: FilmModel) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, filmModel)
                }
                startActivity(intent)
            }
        })
    }
}