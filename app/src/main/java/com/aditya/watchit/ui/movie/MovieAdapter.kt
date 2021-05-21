package com.aditya.watchit.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.FilmListItemBinding
import com.bumptech.glide.Glide

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var onItemClick: OnClickedItem
    private val listMovie = ArrayList<FilmModel>()

    fun setListFilm(data: List<FilmModel>?){
        if (data != null) {
            this.listMovie.clear()
            this.listMovie.addAll(data)
        }
    }

    fun setOnItemClickedCallback(onClickedItem: OnClickedItem){
        this.onItemClick = onClickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: FilmListItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(filmModel: FilmModel){
                Glide.with(itemView.context)
                    .load(filmModel.banner)
                    .into(binding.imgFilm)
                binding.tvTitleFilm.text = filmModel.title
                binding.tvDescriptionFilm.text = filmModel.description
                itemView.setOnClickListener { onItemClick.onClickedItemCallback(filmModel, binding.imgFilm) }
            }
    }
}