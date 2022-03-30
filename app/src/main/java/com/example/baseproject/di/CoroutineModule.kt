package com.example.baseproject.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coroutineModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }
}