package com.example.baseproject.di

import com.example.baseproject.data.network.buildOkHttpClient
import com.example.baseproject.data.network.provideGsonConverterFactory
import com.example.baseproject.data.network.provideProductApiService
import com.example.baseproject.data.network.provideProductRetrofit
import org.koin.dsl.module

val networkModule = module {
    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }
}
