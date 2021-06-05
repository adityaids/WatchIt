package com.aditya.watchit.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.databinding.ActivityDetailBinding
import com.aditya.watchit.viewmodel.ViewModelFactory
import com.aditya.watchit.vo.Status
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA_POPULAR: String = "extra_data_popular"
        const val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        if (intent.hasExtra(EXTRA_DATA_POPULAR)) {
           val popular = intent.getParcelableExtra<PopularEntity>(EXTRA_DATA_POPULAR) as PopularEntity
            detailViewModel.setFilm(popular.title, popular.type)
        }
        if (intent.hasExtra(EXTRA_DATA)) {
            val film = intent.getParcelableExtra<FilmEntity>(EXTRA_DATA) as FilmEntity
            detailViewModel.setFilm(film.title, film.type)
        }
        detailViewModel.getFilm().observe(this, {
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> binding.pgsBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.pgsBar.visibility = View.GONE
                        populateFilm(it.data)
                    }
                    Status.ERROR -> {
                        binding.pgsBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun populateFilm(result: FilmEntity?){
        if (result != null) {
            val imageSource: Int = resources.getIdentifier(result.banner, "drawable", packageName)
            val drawable = ContextCompat.getDrawable(this, imageSource)
            Glide.with(this)
                .load(drawable)
                .into(binding.imgDetail)
            binding.tvType.text = result.type
            binding.tvDetailTitle.text = result.title
            binding.tvDetailDescription.text = result.description
            binding.btnFavorit.setOnClickListener { addToFavorit(result) }
        }
    }

    private fun addToFavorit(filmEntity: FilmEntity){
        detailViewModel.addToFavorit(filmEntity)
        Toast.makeText(this, "Added To Favorit", Toast.LENGTH_LONG).show()
    }
}