package com.aditya.watchit.ui.movie

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.R
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.FragmentMovieBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.ui.main.MainViewModel

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
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
            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
            val listMovie = viewModel.getMovieList()
            movieAdapter.setListFilm(listMovie)

            movieAdapter.setOnItemClickedCallback(object : OnClickedItem{
                override fun onClickedItemCallback(filmModel: FilmModel, imageView: View) {
                    val imagePair = Pair.create(imageView, DetailActivity.EXTRA_IMAGE_TRANSITION)

                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DATA, filmModel)
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val activityOption = ActivityOptions.makeSceneTransitionAnimation(activity, imagePair)
                        startActivity(intent, activityOption.toBundle())
                    } else {
                        startActivity(intent)
                    }
                }
            })
        }
    }
}