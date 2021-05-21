package com.aditya.watchit.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
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
                Glide.with(itemView.context)
                    .load(filmModel.banner)
                    .into(binding.imgPopular)
                binding.tvTitle.text = filmModel.title
                itemView.setOnClickListener {onItemClick.onClickedItemCallback(filmModel, binding.imgPopular)}
            }
    }
}