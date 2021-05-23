package com.aditya.watchit.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var filmModel: FilmModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        if (intent.hasExtra(EXTRA_DATA)) {
           filmModel = intent.getParcelableExtra<FilmModel>(EXTRA_DATA) as FilmModel
            detailViewModel.setTitle(filmModel.title, filmModel.type)
            val film = detailViewModel.getFilm()
            PopulateFilm(film)
        }
    }

    private fun PopulateFilm(filmModel: FilmModel){
        Glide.with(this)
                .load(filmModel.banner)
                .into(binding.imgDetail)
        binding.tvType.text = filmModel.type
        binding.tvDetailTitle.text = filmModel.title
        binding.tvDetailDescription.text = filmModel.description
    }
}