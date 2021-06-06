package com.aditya.watchit.ui.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.databinding.FavoritItemBinding

class FavoritAdapter: RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {
    private val listFavorit = ArrayList<FavoritEntity>()
    private lateinit var onItemClick: OnClickedItem

    fun setFavorit(data: List<FavoritEntity>){
        listFavorit.clear()
        listFavorit.addAll(data)
    }

    fun setOnItemClickedCallback(onClickedItem: OnClickedItem){
        this.onItemClick = onClickedItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val binding = FavoritItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val film = listFavorit[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = listFavorit.size

    inner class FavoritViewHolder(private val binding: FavoritItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(favoritEntity: FavoritEntity){

            }
    }
}