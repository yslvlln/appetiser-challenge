package com.challenge.itunes.di

import com.challenge.itunes.viewmodel.ItunesMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel { ItunesMainViewModel(repository = get()) }
}