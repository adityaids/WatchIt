package com.aditya.watchit.ui.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.databinding.FilmListItemBinding
import com.bumptech.glide.Glide

class TvSeriesAdapter: PagedListAdapter<FilmEntity, TvSeriesAdapter.TvViewHolder>(DIFF_CALLBACK){
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

    fun setOnItemClickCallback(onClickedItem: OnClickedItem){
        this.onItemClick = onClickedItem
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = FilmListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvSeries = getItem(position)
        if (tvSeries != null) {
            holder.bind(tvSeries)
        }
    }

    inner class TvViewHolder(private val binding: FilmListItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(filmModel: FilmEntity){
                val imageSource: Int = itemView.context.resources.getIdentifier(filmModel.banner, "drawable", itemView.context.packageName)
                val drawable = AppCompatResources.getDrawable(itemView.context, imageSource)
                Glide.with(itemView.context)
                    .load(drawable)
                    .into(binding.imgFilm)
                binding.tvTitleFilm.text = filmModel.title
                binding.tvDescriptionFilm.text = filmModel.description
                itemView.setOnClickListener { onItemClick.onClickedItemCallback(filmModel) }
            }
    }
}