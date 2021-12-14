package com.challenge.itunes.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.itunes.R
import com.challenge.itunes.adapter.TrendingMovieAdapter
import com.challenge.itunes.adapter.TrendingMovieCategoryAdapter
import com.challenge.itunes.data.model.TrendMoviesResponseResult
import com.challenge.itunes.databinding.FragmentListBinding
import com.challenge.itunes.utilities.constant.MovieCategory
import com.challenge.itunes.utilities.extension.gone
import com.challenge.itunes.utilities.extension.hideToolbar
import com.challenge.itunes.utilities.extension.setSafeOnClickListener
import com.challenge.itunes.utilities.extension.visible
import com.challenge.itunes.viewmodel.ItunesMainViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ItunesMainViewModel by sharedViewModel()
    private val trendingAdapter: TrendingMovieAdapter by lazy { TrendingMovieAdapter(requireContext()) }
    private val categoryAdapter: TrendingMovieCategoryAdapter by lazy {
        TrendingMovieCategoryAdapter(requireContext(), callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        initData()
        setupUi()
        setupActions()
        setupObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        viewModel.getTrendingMovies(MovieCategory.ACTION.type)
    }

    private fun setupUi() {
        hideToolbar()
        //Setup rvs
        binding.trendingLayout.rvTrendingMovies.apply {
            adapter = trendingAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
        }
        binding.rvTrendingCategory.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupActions() {
        binding.trendingLayout.recommendedMoviesBtn.setSafeOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_recommendedFragment)
        }
    }

    private fun setupObservers() {
        viewModel.trendMovies.observe(viewLifecycleOwner, { result ->
            result?.let { it ->
                showPlaceholder(it)
                trendingAdapter.setData(it)
                //Scroll the screen to top most after updating the list
                binding.mainListScroll.smoothScrollTo(0,0)
            }
        })
        viewModel.isTrendLoading.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                binding.trendProgressBar.visible()
            } else {
                binding.trendProgressBar.gone()
            }
        })
    }

    private fun showPlaceholder(trendMovies: List<TrendMoviesResponseResult>) {
        if (trendMovies.isNullOrEmpty()) {
            binding.emptyListLayout.root.visible()
            binding.trendingLayout.recommendedMoviesLl.gone()
        } else {
            binding.emptyListLayout.root.gone()
            binding.trendingLayout.recommendedMoviesLl.visible()
        }
    }

    private val callback = object : TrendingMovieCategoryAdapter.CategoryCallback {
        override fun onCategoryClick(category: String) {
            viewModel.getTrendingMovies(category)
        }
    }
}