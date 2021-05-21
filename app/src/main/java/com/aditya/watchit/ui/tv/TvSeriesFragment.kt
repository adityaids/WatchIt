package com.aditya.watchit.ui.tv

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
import com.aditya.watchit.databinding.FragmentTvSeriesBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.ui.main.MainViewModel


class TvSeriesFragment : Fragment() {
    private lateinit var binding: FragmentTvSeriesBinding
    private val tvSeriesAdapter = TvSeriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        binding.rvTvSeries.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvSeriesAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]
            val tvList = viewModel.getTvList()
            tvSeriesAdapter.setTvSeries(tvList)
            tvSeriesAdapter.setOnItemClickCallback(object : OnClickedItem{
                override fun onClickedItemCallback(filmModel: FilmModel) {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_DATA, filmModel)
                    }
                    startActivity(intent)
                }
            })
        }
    }
}