package com.challenge.itunes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.challenge.itunes.databinding.FragmentListBinding
import com.challenge.itunes.utilities.extension.hideToolbar
import com.challenge.itunes.utilities.extension.setSafeOnClickListener

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        setupUi()
        setupActions()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        hideToolbar()
    }

    private fun setupActions() {
        binding.searchBtn.setSafeOnClickListener {  }
    }
}