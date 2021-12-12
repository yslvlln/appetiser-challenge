package com.challenge.itunes.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.challenge.itunes.R
import com.challenge.itunes.utilities.SPLASH_TIME
import com.challenge.itunes.utilities.extension.hideToolbar
import com.challenge.itunes.utilities.extension.redirect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        hideToolbar()
        lifecycleScope.launch {
            delay(SPLASH_TIME)
            redirect().navigate(R.id.action_splashFragment_to_listFragment)
        }
    }
}