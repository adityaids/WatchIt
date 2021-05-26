package com.aditya.watchit.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.watchit.data.FilmModel
import com.aditya.watchit.data.OnClickedItem
import com.aditya.watchit.databinding.FragmentTvSeriesBinding
import com.aditya.watchit.ui.detail.DetailActivity
import com.aditya.watchit.ui.main.MainViewModel


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
                binding.pgsBar.visibility = View.GONE
                tvSeriesAdapter.setTvSeries(it)
                tvSeriesAdapter.notifyDataSetChanged()
            })

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