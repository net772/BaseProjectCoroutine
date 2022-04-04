package com.example.baseproject.di

import com.example.baseproject.ui.list.ProductListViewModel
import com.example.baseproject.ui.main.MainViewModel
import com.example.baseproject.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel() }
}
