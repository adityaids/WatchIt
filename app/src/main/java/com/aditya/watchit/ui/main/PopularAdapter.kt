package com.aditya.watchit.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.databinding.PopulerFilmItemBinding
import com.bumptech.glide.Glide

class PopularAdapter: PagedListAdapter<PopularEntity, PopularAdapter.PopularViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClick: OnClickPopularFilm

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularEntity>() {
            override fun areItemsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
                return oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: PopularEntity, newItem: PopularEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
    fun setOnItemClick(onClickedItem: OnClickPopularFilm){
        this.onItemClick = onClickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopulerFilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val film = getItem(position)
        if (film != null) {
            holder.bind(film)
        }
    }

    inner class PopularViewHolder(private val binding: PopulerFilmItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(result: PopularEntity){
                val imageSource: Int = itemView.context.resources.getIdentifier(result.banner, "drawable", itemView.context.packageName)
                val drawable = ContextCompat.getDrawable(itemView.context, imageSource)
                Glide.with(itemView.context)
                    .load(drawable)
                    .into(binding.imgPopular)
                binding.tvTitle.text = result.title
                itemView.setOnClickListener {onItemClick.onClickItem(result)}
            }
    }

    interface OnClickPopularFilm{
        fun onClickItem(popularEntity: PopularEntity)
    }
}