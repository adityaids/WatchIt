package com.aditya.watchit.ui.tv

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
import com.aditya.watchit.databinding.FragmentTvSeriesBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.ui.main.MainViewModel
import com.aditya.watchit.vo.Status


class TvSeriesFragment : Fragment() {
    private lateinit var binding: FragmentTvSeriesBinding
    private lateinit var tvSeriesAdapter: TvSeriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSeriesBinding.inflate(layoutInflater, container, false)
        tvSeriesAdapter = TvSeriesAdapter()
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
            binding.pgsBar.visibility = View.VISIBLE
            viewModel.getTvList().observe(viewLifecycleOwner,{
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> binding.pgsBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.pgsBar.visibility = View.GONE
                            tvSeriesAdapter.setTvSeries(it.data)
                            tvSeriesAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding.pgsBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            tvSeriesAdapter.setOnItemClickCallback(object : OnClickedItem{
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