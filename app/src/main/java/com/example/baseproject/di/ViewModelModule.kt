package com.example.baseproject.di

import com.example.baseproject.ui.detail.ProductDetailViewModel
import com.example.baseproject.ui.list.ProductListViewModel
import com.example.baseproject.ui.main.MainViewModel
import com.example.baseproject.ui.profile.ProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(androidApplication()) }
    viewModel { ProductListViewModel(androidApplication(), get()) }
    viewModel { ProfileViewModel(androidApplication()) }
    viewModel { (productId: String) -> ProductDetailViewModel(productId, androidApplication(), get()) }
}
