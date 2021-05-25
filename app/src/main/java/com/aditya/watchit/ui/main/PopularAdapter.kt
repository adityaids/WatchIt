package com.aditya.watchit.ui.main

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.PopulerFilmItemBinding
import com.bumptech.glide.Glide

class PopularAdapter: RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    private lateinit var onItemClick: OnClickedItem
    private val listPopulerFilm = ArrayList<FilmModel>()

    fun setPopularFilm(data: List<FilmModel>?){
        if (data == null) return
        this.listPopulerFilm.clear()
        this.listPopulerFilm.addAll(data)
    }
    fun setOnItemClick(onClickedItem: OnClickedItem){
        this.onItemClick = onClickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopulerFilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val film = listPopulerFilm[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = listPopulerFilm.size

    inner class PopularViewHolder(private val binding: PopulerFilmItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(filmModel: FilmModel){
                val imageSource: Int = itemView.context.resources.getIdentifier(filmModel.banner, "drawable", itemView.context.packageName)
                val drawable = ContextCompat.getDrawable(itemView.context, imageSource)
                Glide.with(itemView.context)
                    .load(drawable)
                    .into(binding.imgPopular)
                binding.tvTitle.text = filmModel.title
                itemView.setOnClickListener {onItemClick.onClickedItemCallback(filmModel)}
            }
    }
}