package com.aditya.watchit.ui.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.ActivityMainBinding
import com.aditya.watchit.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private val popularAdapter = PopularAdapter()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularAdapter
        }
        val popularList = mainViewModel.getMovieList()
        popularAdapter.setPopularFilm(popularList)

        popularAdapter.setOnItemClick(object : OnClickedItem{
            override fun onClickedItemCallback(filmModel: FilmModel, imageView: View) {
                val imagePair = Pair.create(imageView, DetailActivity.EXTRA_IMAGE_TRANSITION)

                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA, filmModel)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val activityOption = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, imagePair)
                    startActivity(intent, activityOption.toBundle())
                } else {
                    startActivity(intent)
                }
            }
        })
    }
}