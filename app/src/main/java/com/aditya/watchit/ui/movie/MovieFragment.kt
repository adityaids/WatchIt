package com.aditya.watchit.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.data.source.local.entity.FilmEntity
import com.aditya.watchit.databinding.FragmentMovieBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.ui.main.MainViewModel
import com.aditya.watchit.vo.Status

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        movieAdapter = MovieAdapter()
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            binding.pgsBar.visibility = View.VISIBLE
            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
            viewModel.getMovieList().observe(viewLifecycleOwner,{
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.pgsBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.pgsBar.visibility = View.GONE
                            movieAdapter.setListFilm(it.data)
                            movieAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.pgsBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            movieAdapter.setOnItemClickedCallback(object : OnClickedItem{
                override fun onClickedItemCallback(filmEntity: FilmEntity) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DATA, filmEntity)
                    }
                    startActivity(intent)
                }
            })
        }
    }
}