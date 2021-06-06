package com.aditya.watchit.ui.favorit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aditya.watchit.R
import com.aditya.watchit.data.source.local.entity.FavoritEntity
import com.aditya.watchit.databinding.ActivityFavoritBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class FavoritActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritBinding
    private lateinit var viewModel: FavoritViewModel
    private val favoritAdapter = FavoritAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemTouchHelper.attachToRecyclerView(binding.rvFavorit)
        binding.pgsBar.visibility = View.VISIBLE
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[FavoritViewModel::class.java]

        viewModel.getAllFavorit().observe(this,{
            if (it != null) {
                binding.pgsBar.visibility = View.GONE
                favoritAdapter.submitList(it)
                favoritAdapter.notifyDataSetChanged()
            } else {
                binding.pgsBar.visibility = View.GONE
                binding.tvNoFavorit.visibility = View.VISIBLE
            }
            binding.rvFavorit.apply {
                layoutManager = LinearLayoutManager(this@FavoritActivity)
                setHasFixedSize(true)
                adapter = favoritAdapter
            }
        })
        favoritAdapter.setOnItemClickedCallback(object : FavoritAdapter.OnFavoritClick{
            override fun onItemClicked(favoritEntity: FavoritEntity) {
                val intent = Intent(this@FavoritActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_DATA_FAVORIT, favoritEntity)
                }
                startActivity(intent)
            }

        })
    }
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val view = binding.root
            val swipedPosition = viewHolder.adapterPosition
            val favorit = favoritAdapter.getSwipedData(swipedPosition)
            favorit?.let { viewModel.deleteFavorit(it) }
            val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
            snackbar.setAction(R.string.message_ok) { v ->
                favorit?.let { viewModel.undoDelete(it) }
            }
            snackbar.show()
        }
    })
}