package com.aditya.watchit.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.databinding.ActivityDetailBinding
import com.aditya.watchit.viewmodel.ViewModelFactory
import com.aditya.watchit.vo.Status
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

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
        if (intent.hasExtra(EXTRA_DATA)) {
           filmModel = intent.getParcelableExtra<FilmModel>(EXTRA_DATA) as FilmModel
            detailViewModel.setFilm(filmModel.title, filmModel.type)
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
        binding.btnFavorit.setOnClickListener { addToFavorit(filmModel) }
    }

    private fun populateFilm(result: FilmEntity?){
        if (result != null) {
            val imageSource: Int = resources.getIdentifier(filmModel.banner, "drawable", packageName)
            val drawable = ContextCompat.getDrawable(this, imageSource)
            Glide.with(this)
                .load(drawable)
                .into(binding.imgDetail)
            binding.tvType.text = filmModel.type
            binding.tvDetailTitle.text = filmModel.title
            binding.tvDetailDescription.text = filmModel.description
        }
    }

    private fun addToFavorit(filmModel: FilmModel){
        detailViewModel.addToFavorit(filmModel)
        Toast.makeText(this, "Added To Favorit", Toast.LENGTH_LONG).show()
    }
}