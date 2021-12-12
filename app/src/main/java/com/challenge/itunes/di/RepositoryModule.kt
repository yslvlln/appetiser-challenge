package com.challenge.itunes.di

import com.challenge.itunes.data.repository.ItunesRepository
import com.challenge.itunes.data.repository.ItunesRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single<ItunesRepository> { ItunesRepositoryImpl(apiService = get()) }
}