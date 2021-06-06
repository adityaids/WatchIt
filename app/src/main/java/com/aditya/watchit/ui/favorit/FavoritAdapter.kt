package com.aditya.watchit.ui.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.data.source.local.entity.PopularEntity
import com.aditya.watchit.databinding.FilmListItemBinding
import com.bumptech.glide.Glide

class FavoritAdapter: PagedListAdapter<FavoritEntity,FavoritAdapter.FavoritViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoritEntity>() {
            override fun areItemsTheSame(oldItem: FavoritEntity, newItem: FavoritEntity): Boolean {
                return oldItem.title == newItem.title
            }
            override fun areContentsTheSame(oldItem: FavoritEntity, newItem: FavoritEntity): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
    private lateinit var onItemClick: OnFavoritClick

    fun setOnItemClickedCallback(onClickedItem: OnFavoritClick){
        this.onItemClick = onClickedItem
    }

    fun getSwipedData(swipedPosition: Int): FavoritEntity? = getItem(swipedPosition)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val binding = FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val film = getItem(position)
        if (film != null) {
            holder.bind(film)
        }
    }

    inner class FavoritViewHolder(private val binding: FilmListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(favoritEntity: FavoritEntity){
                val imageSource: Int = itemView.context.resources.getIdentifier(favoritEntity.banner, "drawable", itemView.context.packageName)
                val drawable = ContextCompat.getDrawable(itemView.context, imageSource)
                Glide.with(itemView.context)
                    .load(drawable)
                    .into(binding.imgFilm)
                binding.tvTitleFilm.text = favoritEntity.title
                binding.tvDescriptionFilm.text = favoritEntity.description
                itemView.setOnClickListener { onItemClick.onItemClicked(favoritEntity) }
            }
    }

    interface OnFavoritClick{
        fun onItemClicked(favoritEntity: FavoritEntity)
    }
}