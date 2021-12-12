package com.challenge.itunes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.challenge.itunes.R
import com.challenge.itunes.viewmodel.ItunesMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ItunesMainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setupUi()
    }

    private fun initData() {
        viewModel.getAllMusic()
    }

    private fun setupUi() {
        setContentView(R.layout.activity_main)
    }
}