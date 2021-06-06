package com.aditya.watchit.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.databinding.FilmListItemBinding
import com.bumptech.glide.Glide

class MovieAdapter: PagedListAdapter<FilmEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilmEntity>() {
            override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
    private lateinit var onItemClick: OnClickedItem

    fun setOnItemClickedCallback(onClickedItem: OnClickedItem){
        this.onItemClick = onClickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: FilmListItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(filmModel: FilmEntity){
                val imageSource: Int = itemView.context.resources.getIdentifier(filmModel.banner, "drawable", itemView.context.packageName)
                val drawable = ContextCompat.getDrawable(itemView.context, imageSource)
                Glide.with(itemView.context)
                    .load(drawable)
                    .into(binding.imgFilm)
                binding.tvTitleFilm.text = filmModel.title
                binding.tvDescriptionFilm.text = filmModel.description
                itemView.setOnClickListener { onItemClick.onClickedItemCallback(filmModel) }
            }
    }
}