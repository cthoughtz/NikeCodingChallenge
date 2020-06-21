package com.example.nikecodingchallenge.di

import com.example.nikecodingchallenge.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        AppViewModel()
    }
}