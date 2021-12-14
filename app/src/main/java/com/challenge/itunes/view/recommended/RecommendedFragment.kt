package com.challenge.itunes.view.recommended

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.itunes.R
import com.challenge.itunes.adapter.RecoMovieAdapter
import com.challenge.itunes.databinding.FragmentRecommendedBinding
import com.challenge.itunes.utilities.extension.gone
import com.challenge.itunes.utilities.extension.observeOnce
import com.challenge.itunes.utilities.extension.showToolbar
import com.challenge.itunes.utilities.extension.visible
import com.challenge.itunes.view.MainActivity
import com.challenge.itunes.viewmodel.ItunesMainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * This fragment displays the result of the
 * provided in the email which is:
 * https://itunes.apple.com/search?term=star&country=au&media=movie&all
 *
 * Displayed the ff:
 * Track name, artwork (movie logo), price, and genre
 */
class RecommendedFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentRecommendedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ItunesMainViewModel by sharedViewModel()

    private val recoAdapter: RecoMovieAdapter by lazy { RecoMovieAdapter(activity as MainActivity) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecommendedBinding.inflate(inflater, container, false)
        initData()
        setupUi()
        setupObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.reco_menu, menu)
        // Setup search menu
        val searchMenu = menu.findItem(R.id.reco_search)
        val searchView = searchMenu.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initData() {
        viewModel.getRecommendedMovies()
    }

    private fun setupUi() {
        setHasOptionsMenu(true)
        showToolbar()
        //Setup rvs
        binding.recommendedRv.apply {
            adapter = recoAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupObservers() {
        viewModel.recoMovies.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                binding.emptyListLayout.root.visible()
            } else {
                binding.emptyListLayout.root.gone()
                recoAdapter.setData(it)
            }
        })
        viewModel.isTrendLoading.observe(viewLifecycleOwner, {
            if (it) {
                binding.recoProgressBar.visible()
            } else {
                binding.recoProgressBar.gone()
            }
        })
    }

    private fun setupSearchObserver(searchQuery: String) {
        viewModel.searchDatabase(searchQuery).observeOnce(viewLifecycleOwner, { result ->
            result?.let {
                recoAdapter.setData(result)
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            setupSearchObserver("%$query%")
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            setupSearchObserver("%$newText%")
        }
        return true
    }
}